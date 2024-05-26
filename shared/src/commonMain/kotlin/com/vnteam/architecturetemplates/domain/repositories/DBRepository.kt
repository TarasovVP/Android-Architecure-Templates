package com.vnteam.architecturetemplates.domain.repositories

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    suspend fun clearForks()

    suspend fun insertForksToDB(forks: List<Fork>): Flow<Unit>

    suspend fun getForksFromDB(): Flow<List<Fork>>

    suspend fun getForkById(forkId: Long): Flow<Fork?>

    suspend fun deleteForkById(forkId: Long): Flow<Unit>
}