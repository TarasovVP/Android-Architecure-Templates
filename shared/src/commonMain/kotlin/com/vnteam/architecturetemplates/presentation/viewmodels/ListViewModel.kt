package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ListUseCase
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val listUseCase: ListUseCase,
    private val forkUIMapper: ForkUIMapper,
    screenState: MutableState<ScreenState>
) : BaseViewModel(screenState) {

    private val _state = MutableStateFlow(ListViewState())
    val state: StateFlow<ListViewState> = _state.asStateFlow()

    fun processIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.ClearForks -> clearForks()
            is ListIntent.LoadForks -> getForksFromApi(intent.isInit)
            is ListIntent.DeleteFork -> deleteForkById(intent.id)
        }
    }

    private fun clearForks() {
        viewModelScope.launch(exceptionHandler) {
            listUseCase.clearForks()
            getForksFromApi(true)
        }
    }

    private fun getForksFromApi(isInit: Boolean) {
        if (isInit) showProgress(true)
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
                _state.value = state.value.copy(forks = forks)
                showProgress(false)
            }
        }
    }

    private fun deleteForkById(forkId: String) {
        showProgress(true)
        _state.value = state.value.copy(successResult = false)
        viewModelScope.launch(exceptionHandler) {
            listUseCase.deleteForkById(forkId).collect {
                _state.value = state.value.copy(successResult = true)
                showMessage("Successfully deleted", false)
                showProgress(false)
            }
        }
    }
}