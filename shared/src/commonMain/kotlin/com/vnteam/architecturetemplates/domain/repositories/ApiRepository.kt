package com.vnteam.architecturetemplates.domain.repositories

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getForksFromApi(): Flow<List<Fork>?>

    suspend fun insertForksToApi(forks: List<Fork>?): Flow<Unit>

    suspend fun getForkById(forkId: String?): Flow<Fork?>

    suspend fun deleteForkById(forkId: String): Flow<Unit>
}