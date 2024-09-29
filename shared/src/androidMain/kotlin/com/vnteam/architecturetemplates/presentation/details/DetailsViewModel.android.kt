package com.vnteam.architecturetemplates.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

actual class DetailsViewModel actual constructor(
    private val forkRepository: DBRepository,
    private val forkUIMapper: ForkUIMapper,
): ViewModel() {

    private val _state = MutableStateFlow(DetailsViewState())
    actual val state: StateFlow<DetailsViewState> = _state.asStateFlow()

    actual fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadFork -> getForkById(intent.forkId)
            else -> Unit
        }
    }

    actual fun getForkById(forkId: Long?) {
        viewModelScope.launch {
            forkRepository.getForkById(forkId ?: 0)
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = _state.value.copy(error = exception.localizedMessage, isLoading = false)
                }
                .collect { fork ->
                    _state.value = _state.value.copy(fork = fork?.let { forkUIMapper.mapToImplModel(it) }, isLoading = false)
                }
        }
    }
}