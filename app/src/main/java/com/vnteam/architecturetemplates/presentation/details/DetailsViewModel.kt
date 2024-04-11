package com.vnteam.architecturetemplates.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.mappers.DemoObjectUIMapper
========
import com.vnteam.architecturetemplates.domain.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
>>>>>>>> 49c2f929 (Init branch):app/src/main/java/com/vnteam/architecturetemplates/presentation/details/DetailsViewModel.kt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val demoObjectRepository: DBRepository,
    private val demoObjectUIMapper: DemoObjectUIMapper,
): ViewModel() {

    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState> = _state.asStateFlow()

    fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadDemoObject -> getDemoObjectById(intent.demoObjectId)
            else -> Unit
        }
    }

    private fun getDemoObjectById(demoObjectId: Long?) {
        viewModelScope.launch {
            demoObjectRepository.getDemoObjectById(demoObjectId ?: 0)
                .onStart {
                    _state.value = _state.value.copy(isLoading = true)
                }
                .catch { exception ->
                    _state.value = _state.value.copy(error = exception.message, isLoading = false)
                }
                .collect { demoObject ->
                    _state.value = _state.value.copy(demoObject = demoObject?.let { demoObjectUIMapper.mapToImplModel(it) }, isLoading = false)
                }
        }
    }
}