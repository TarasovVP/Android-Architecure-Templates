package com.vnstudio.cleanarchitecturedemo.presentation.usecaseimpl

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import com.vnstudio.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnstudio.cleanarchitecturedemo.domain.usecase.ForkUseCase

class ForkUseCaseImpl(private val apiRepository: ApiRepository, private val dbRepository: DBRepository) :
    ForkUseCase {

    override suspend fun getForksFromApi(): List<Fork>? {
        return apiRepository.getForksFromApi()
    }

    override fun insertForksToDB(forks: List<Fork>) {
        dbRepository.insertForksToDB(forks)
    }

    override fun getForksFromDB(): List<Fork> {
        return dbRepository.getForksFromDB()
    }

    override fun getForkById(forkId: Long): Fork {
        return dbRepository.getForkById(forkId)
    }
}