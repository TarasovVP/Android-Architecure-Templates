package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface DetailsUseCase {

    suspend fun getForkById(id: Long): Flow<Fork?>
}