package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ForkUseCase {

    suspend fun getForksFromApi(): Flow<List<Fork>?>

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): Flow<List<Fork>>

    fun getForkById(forkId: Long): Flow<Fork?>
}