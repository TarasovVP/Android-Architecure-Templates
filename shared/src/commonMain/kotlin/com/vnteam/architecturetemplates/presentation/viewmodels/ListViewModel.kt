package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val forkUseCase: ForkUseCase,
    private val forkUIMapper: ForkUIMapper
) : ViewModel() {

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.value = state.value.copy(isLoading = false, error = exception.message)
    }

    fun processIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.LoadForks -> getForksFromApi()
        }
    }

    private fun getForksFromApi() {
        viewModelScope.launch(exceptionHandler) {
            _state.value = state.value.copy(isLoading = true)
            val forks = forkUseCase.getForksFromApi()
            insertForksToDB(forks)
        }
    }

    private fun insertForksToDB(forks: Flow<List<Fork>?>) {
        viewModelScope.launch(exceptionHandler) {
            forks.collect {
                it ?: return@collect
                forkUseCase.insertForksToDB(it)
            }
            getForksFromDB()
        }
    }

    private fun getForksFromDB() {
        viewModelScope.launch(exceptionHandler) {
            forkUseCase.getForksFromDB().collect {
                val forks = forkUIMapper.mapToImplModelList(it)
                _state.value = state.value.copy(forks = forks, isLoading = false)
            }
        }
    }
}