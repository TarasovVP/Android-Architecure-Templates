package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ListUseCase {

    suspend fun clearForks()

    suspend fun getForksFromApi(): Flow<List<Fork>?>

    suspend fun insertForksToDB(forks: List<Fork>): Flow<Unit>

    suspend fun getForksFromDB(): Flow<List<Fork>>
}