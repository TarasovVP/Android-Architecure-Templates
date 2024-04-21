package com.vnteam.architecturetemplates.presentation.list

import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import kotlinx.coroutines.flow.StateFlow

expect class ListViewModel(
    forkUseCase: ForkUseCase,
    forkUIMapper: ForkUIMapper,
) {
    val state: StateFlow<ListViewState>

    fun processIntent(intent: ListIntent)
}