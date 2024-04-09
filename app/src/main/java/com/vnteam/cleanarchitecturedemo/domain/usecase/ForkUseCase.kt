package com.vnteam.cleanarchitecturedemo.domain.usecase

import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ForkUseCase {

    suspend fun getForksFromApi(): Flow<List<Fork>?>

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): Flow<List<Fork>>

    fun getForkById(forkId: Long): Flow<Fork?>
}