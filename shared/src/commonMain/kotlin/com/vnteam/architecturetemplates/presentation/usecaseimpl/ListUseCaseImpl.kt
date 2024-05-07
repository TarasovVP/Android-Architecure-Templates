package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import kotlinx.coroutines.flow.Flow

class ForkUseCaseImpl(private val apiRepository: ApiRepository, private val dbRepository: DBRepository) :
    ForkUseCase {

    override suspend fun getForksFromApi(): Flow<List<Fork>?> {
        return apiRepository.getForksFromApi()
    }

    override suspend fun insertForksToDB(forks: List<Fork>) {
        dbRepository.insertForksToDB(forks)
    }

    override suspend fun getForksFromDB(): Flow<List<Fork>> {
        return dbRepository.getForksFromDB()
    }

    override suspend fun getForkById(forkId: Long): Flow<Fork?> {
        return dbRepository.getForkById(forkId)
    }
}