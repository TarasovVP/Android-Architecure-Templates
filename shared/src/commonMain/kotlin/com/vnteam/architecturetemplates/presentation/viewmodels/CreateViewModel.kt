package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.usecase.CreateUseCase
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateViewModel(
    private val createUseCase: CreateUseCase,
    private val forkUIMapper: ForkUIMapper,
    screenState: MutableState<ScreenState>
) : BaseViewModel(screenState) {

    private val _state = MutableStateFlow(CreateViewState())
    val state: StateFlow<CreateViewState> = _state.asStateFlow()

    fun processIntent(intent: CreateIntent) {
        when (intent) {
            is CreateIntent.LoadFork -> getForkById(intent.forkId)
            is CreateIntent.CreateFork -> createFork(state.value.fork.value)
        }
    }

    private fun getForkById(forkId: String?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            createUseCase.getForkById(forkId.orEmpty()).collect { fork ->
                showProgress(false)
                _state.value = _state.value.copy(fork = mutableStateOf( fork?.let { forkUIMapper.mapToImplModel(it) }))
            }
        }
    }

    private fun createFork(fork: ForkUI?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            createUseCase.createFork(fork?.let { forkUIMapper.mapFromImplModel(it) } ?: Fork()).collect {
                insertForkToDB(fork)
            }
        }
    }

    private fun insertForkToDB(fork: ForkUI?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            createUseCase.insertForkToDB(fork?.let { forkUIMapper.mapFromImplModel(it) } ?: Fork()).collect { fork ->
                showProgress(false)
                showMessage("Successfully created", false)
                _state.value = state.value.copy(successResult = true)
            }
        }
    }
}