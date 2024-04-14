package com.vnteam.architecturetemplates.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
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

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState> = _state.asStateFlow()

    fun processIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.LoadForks -> getForksFromApi()
        }
    }

    fun getForksFromApi() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.localizedMessage)
        }) {
            _state.value = state.value.copy(isLoading = true)
            val forks = forkUseCase.getForksFromApi()
            insertForksToDB(forks)
            getForksFromDB()
        }
    }

    private fun insertForksToDB(forks: Flow<List<Fork>?>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.localizedMessage)
        }) {
            forks.collect {
                it ?: return@collect
                forkUseCase.insertForksToDB(it)
            }
        }
    }

    private fun getForksFromDB() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.localizedMessage)
        }) {
            forkUseCase.getForksFromDB().collect {
                val forks = forkUIMapper.mapToImplModelList(it)
                _state.value = state.value.copy(forks = forks, isLoading = false)
            }
        }
    }
}