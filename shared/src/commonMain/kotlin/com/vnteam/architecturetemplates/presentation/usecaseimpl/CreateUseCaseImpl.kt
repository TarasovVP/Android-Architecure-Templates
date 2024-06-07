package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.CreateUseCase
import kotlinx.coroutines.flow.Flow

class CreateUseCaseImpl(private val apiRepository: ApiRepository, private val dbRepository: DBRepository) :
    CreateUseCase {

    override suspend fun getForkById(id: String): Flow<Fork?> {
        return dbRepository.getForkById(id)
    }

    override suspend fun insertForkToDB(fork: Fork): Flow<Unit> {
        return dbRepository.insertForksToDB(listOf(fork))
    }


    override suspend fun createFork(fork: Fork): Flow<Unit> {
        return apiRepository.insertForksToApi(listOf(fork))
    }
}