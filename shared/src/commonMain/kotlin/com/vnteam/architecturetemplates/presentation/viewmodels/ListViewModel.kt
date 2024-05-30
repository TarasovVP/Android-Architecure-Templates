package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ListUseCase
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.states.InfoMessageState
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val listUseCase: ListUseCase,
    private val forkUIMapper: ForkUIMapper
) : ViewModel() {

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = exception.message.orEmpty(), isError = true)))
        println("Error: ${exception.message}")
    }

    fun processIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.ClearForks -> clearForks()
            is ListIntent.LoadForks -> getForksFromApi()
            is ListIntent.DeleteFork -> deleteForkById(intent.id)
        }
    }

    private fun clearForks() {
        viewModelScope.launch(exceptionHandler) {
            listUseCase.clearForks()
        }
    }

    private fun getForksFromApi() {
        _state.value = state.value.copy(isLoading = true)
        viewModelScope.launch(exceptionHandler) {
            listUseCase.getForksFromApi().collect { forks ->
                insertForksToDB(forks)
            }
        }
    }

    private fun insertForksToDB(forks: List<Fork>?) {
        viewModelScope.launch(exceptionHandler) {
            forks?.let {
                listUseCase.insertForksToDB(it).collect {
                    getForksFromDB()
                }
            }
        }
    }

    private fun getForksFromDB() {
        viewModelScope.launch(exceptionHandler) {
            listUseCase.getForksFromDB().collect {
                val forks = forkUIMapper.mapToImplModelList(it)
                _state.value = state.value.copy(forks = forks, isLoading = false)
            }
        }
    }

    private fun deleteForkById(forkId: Long) {
        _state.value = state.value.copy(isLoading = true)
        viewModelScope.launch(exceptionHandler) {
            listUseCase.deleteForkById(forkId).collect {
                _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = "Successfully deleted", isError = false)))
                getForksFromDB()
            }
        }
    }
}