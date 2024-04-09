package com.vnteam.cleanarchitecturedemo.presentation.usecaseimpl

import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnteam.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnteam.cleanarchitecturedemo.domain.usecase.ForkUseCase
import kotlinx.coroutines.flow.Flow

class ForkUseCaseImpl(private val apiRepository: ApiRepository, private val dbRepository: DBRepository) :
    ForkUseCase {

    override suspend fun getForksFromApi(): Flow<List<Fork>?> {
        return apiRepository.getForksFromApi()
    }

    override fun insertForksToDB(forks: List<Fork>) {
        dbRepository.insertForksToDB(forks)
    }

    override fun getForksFromDB(): Flow<List<Fork>> {
        return dbRepository.getForksFromDB()
    }

    override fun getForkById(forkId: Long): Flow<Fork?> {
        return dbRepository.getForkById(forkId)
    }
}