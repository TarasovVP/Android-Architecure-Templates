package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/viewmodels/DetailsViewModel.kt
import com.vnteam.architecturetemplates.domain.usecase.DetailsUseCase
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
import com.vnteam.architecturetemplates.presentation.mappers.DemoObjectUIMapper
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import com.vnteam.architecturetemplates.presentation.states.InfoMessageState
========
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
>>>>>>>> c0ecbca7 (Fix merge conflicts):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/viewmodels/DetailsViewModel.kt
    private val detailsUseCase: DetailsUseCase,
    private val demoObjectUIMapper: DemoObjectUIMapper
========
    private val forkRepository: DBRepository,
    private val forkUIMapper: ForkUIMapper,
>>>>>>>> c0ecbca7 (Fix merge conflicts):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
): ViewModel() {

    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState> = _state.asStateFlow()

    fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadFork -> getForkById(intent.forkId)
            else -> Unit
        }
    }

<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/viewmodels/DetailsViewModel.kt
    private fun getDemoObjectById(demoObjectId: Long?) {
        viewModelScope.launch {
            detailsUseCase.getDemoObjectById(demoObjectId ?: 0)
========
    private fun getForkById(forkId: Long?) {
        viewModelScope.launch {
            forkRepository.getForkById(forkId ?: 0)
>>>>>>>> c0ecbca7 (Fix merge conflicts):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/viewmodels/DetailsViewModel.kt
                    _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = exception.message.orEmpty(), isError = true)))
                    println("Error: ${exception.message}")
========
                    _state.value = _state.value.copy(error = exception.message, isLoading = false)
>>>>>>>> c0ecbca7 (Fix merge conflicts):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
                }
                .collect { fork ->
                    _state.value = _state.value.copy(fork = fork?.let { forkUIMapper.mapToImplModel(it) }, isLoading = false)
                }
        }
    }
}