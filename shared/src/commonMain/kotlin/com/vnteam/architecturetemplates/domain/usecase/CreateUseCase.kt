package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface CreateUseCase {

    suspend fun getForkById(id: String): Flow<Fork?>

    suspend fun createFork(fork: Fork): Flow<Unit>
}