package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.usecase.CreateUseCase
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import com.vnteam.architecturetemplates.presentation.states.InfoMessageState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CreateViewModel(
    private val createUseCase: CreateUseCase,
    private val forkUIMapper: ForkUIMapper
): ViewModel() {

    private val _state = MutableStateFlow(CreateViewState())
    val state: StateFlow<CreateViewState> = _state.asStateFlow()

    fun processIntent(intent: CreateIntent) {
        when (intent) {
            is CreateIntent.LoadFork -> getForkById(intent.forkId)
            is CreateIntent.CreateFork -> createFork(state.value.fork.value)
        }
    }

    private fun getForkById(forkId: String?) {
        viewModelScope.launch {
            createUseCase.getForkById(forkId.orEmpty())
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = exception.message.orEmpty(), isError = true)))
                    println("Error: ${exception.message}")
                }
                .collect { fork ->
                    _state.value = _state.value.copy(fork = mutableStateOf( fork?.let { forkUIMapper.mapToImplModel(it) }), isLoading = false)
                }
        }
    }

    private fun createFork(fork: ForkUI?) {
        viewModelScope.launch {
            createUseCase.createFork(fork?.let { forkUIMapper.mapFromImplModel(it) } ?: Fork())
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = exception.message.orEmpty(), isError = true)))
                    println("Error: ${exception.message}")
                }.collect {
                _state.value = state.value.copy(isLoading = false, successResult = true, infoMessage = mutableStateOf( InfoMessageState(message = "Successfully created", isError = false)))
            }
        }
    }
}