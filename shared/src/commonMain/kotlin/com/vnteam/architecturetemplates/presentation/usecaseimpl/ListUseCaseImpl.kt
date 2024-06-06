package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ListUseCase
import kotlinx.coroutines.flow.Flow

class ListUseCaseImpl(private val apiRepository: ApiRepository, private val dbRepository: DBRepository) :
    ListUseCase {

    override suspend fun getForksFromApi(): Flow<List<Fork>?> {
        return apiRepository.getForksFromApi()
    }

    override suspend fun clearForks() {
        return dbRepository.clearForks()
    }

    override suspend fun insertForksToDB(forks: List<Fork>): Flow<Unit> {
        return dbRepository.insertForksToDB(forks)
    }

    override suspend fun getForksFromDB(): Flow<List<Fork>> {
        return dbRepository.getForksFromDB()
    }

    override suspend fun deleteForkById(id: Long): Flow<Unit> {
        return apiRepository.deleteForkById(id)
    }
}