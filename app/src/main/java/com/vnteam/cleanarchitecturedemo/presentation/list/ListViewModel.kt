package com.vnteam.cleanarchitecturedemo.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val forkUseCase: ForkUseCase,
    private val forkUIMapper: ForkUIMapper,
) : ViewModel() {

    private val _progressVisibilityFlow = MutableStateFlow(false)
    val progressVisibilityFlow: StateFlow<Boolean> = _progressVisibilityFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow.asStateFlow()

    private val _forksFromDBFlow = MutableStateFlow<List<ForkUI>>(emptyList())
    val forksFromDBFlow: StateFlow<List<ForkUI>> = _forksFromDBFlow.asStateFlow()

    fun getForksFromApi() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _progressVisibilityFlow.value = false
            _errorFlow.value = exception.localizedMessage
        }) {
            _progressVisibilityFlow.value = true
            val forks = forkUseCase.getForksFromApi()
            insertForksToDB(forks)
            getForksFromDB()
        }
    }

    private fun insertForksToDB(forks: Flow<List<Fork>?>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _progressVisibilityFlow.value = false
            _errorFlow.value = exception.localizedMessage
        }) {
            forks.collect {
                it ?: return@collect
                forkUseCase.insertForksToDB(it)
            }
        }
    }

    private fun getForksFromDB() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _progressVisibilityFlow.value = false
            _errorFlow.value = exception.localizedMessage
        }) {
            forkUseCase.getForksFromDB().collect {
                val forks = forkUIMapper.mapToImplModelList(it)
                _forksFromDBFlow.value = forks
                _progressVisibilityFlow.value = false
            }
        }
    }
}