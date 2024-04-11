package com.vnteam.architecturetemplates.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val forkRepository: DBRepository,
    private val forkUIMapper: ForkUIMapper,
): ViewModel() {

    private val _progressVisibilityFlow = MutableStateFlow(false)
    val progressVisibilityFlow: StateFlow<Boolean> = _progressVisibilityFlow

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    private val _forkFlow = MutableStateFlow<ForkUI?>(null)
    val forkFlow: StateFlow<ForkUI?> = _forkFlow

    fun getForkById(forkId: Long?) {
        viewModelScope.launch {
            forkRepository.getForkById(forkId ?: 0)
                .onStart {
                    _progressVisibilityFlow.value = true
                }
                .catch { exception ->
                    _progressVisibilityFlow.value = false
                    _errorFlow.value = exception.localizedMessage
                }
                .collect { fork ->
                    _forkFlow.value = fork?.let { forkUIMapper.mapToImplModel(it) }
                    _progressVisibilityFlow.value = false
                }
        }
    }
}