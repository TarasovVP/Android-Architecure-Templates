package com.vnteam.architecturetemplates.details

import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import kotlinx.coroutines.flow.StateFlow

expect class DetailsViewModel(forkRepository: DBRepository,
                              forkUIMapper: ForkUIMapper) {

    val state: StateFlow<DetailsViewState>

    fun processIntent(intent: DetailsIntent)

    fun getForkById(forkId: Long?)
}