package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.domain.mappers.DemoObjectUIMapper
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateViewModel(
    private val getDemoObjectUseCase: GetDemoObjectUseCase,
    private val insertDemoObjectsUseCase: InsertDemoObjectsUseCase,
    private val createDemoObjectUseCase: CreateDemoObjectUseCase,
    private val demoObjectUIMapper: DemoObjectUIMapper
) : BaseViewModel() {

    private val _state = MutableStateFlow(CreateViewState())
    val state: StateFlow<CreateViewState> = _state.asStateFlow()

    fun processIntent(intent: CreateIntent) {
        when (intent) {
            is CreateIntent.LoadDemoObject -> getDemoObjectById(intent.demoObjectId)
            is CreateIntent.CreateDemoObject -> createDemoObject(state.value.demoObject.value)
        }
    }

    private fun getDemoObjectById(demoObjectId: String?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            getDemoObjectUseCase.execute(demoObjectId.orEmpty()).collect { demoObject ->
                showProgress(false)
                _state.value = _state.value.copy(demoObject = mutableStateOf( demoObject?.let { demoObjectUIMapper.mapToImplModel(it) }))
            }
        }
    }

    private fun createDemoObject(demoObject: DemoObjectUI?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            createDemoObjectUseCase.execute(demoObject?.let { demoObjectUIMapper.mapFromImplModel(it) } ?: DemoObject()).collect {
                insertDemoObjectToDB(demoObject)
            }
        }
    }

    private fun insertDemoObjectToDB(demoObject: DemoObjectUI?) {
        showProgress(true)
        viewModelScope.launch(exceptionHandler) {
            insertDemoObjectsUseCase.execute(listOf( demoObject?.let { demoObjectUIMapper.mapFromImplModel(it) } ?: DemoObject())).collect { _ ->
                showProgress(false)
                showMessage("Successfully created", false)
                _state.value = state.value.copy(successResult = true)
            }
        }
    }
}