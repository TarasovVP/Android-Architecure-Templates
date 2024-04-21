package com.vnteam.architecturetemplates.presentation.list

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

actual class ListViewModel actual constructor(
    private val forkUseCase: ForkUseCase,
    private val forkUIMapper: ForkUIMapper,
) {
    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val _state = MutableStateFlow(ListViewState())
    actual val state: StateFlow<ListViewState> = _state.asStateFlow()

    actual fun processIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.LoadForks -> getForksFromApi()
        }
    }

    private fun getForksFromApi() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.message)
        }) {
            _state.value = state.value.copy(isLoading = true)
            val forks = forkUseCase.getForksFromApi()
            insertForksToDB(forks)
            getForksFromDB()
        }
    }

    private fun insertForksToDB(forks: Flow<List<Fork>?>) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.message)
        }) {
            forks.collect {
                it ?: return@collect
                forkUseCase.insertForksToDB(it)
            }
        }
    }

    private fun getForksFromDB() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            _state.value = state.value.copy(isLoading = false, error = exception.message)
        }) {
            forkUseCase.getForksFromDB().collect {
                val forks = forkUIMapper.mapToImplModelList(it)
                _state.value = state.value.copy(forks = forks, isLoading = false)
            }
        }
    }
}