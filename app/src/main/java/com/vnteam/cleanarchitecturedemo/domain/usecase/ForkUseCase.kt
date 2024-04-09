package com.vnteam.cleanarchitecturedemo.domain.usecase

import com.vnteam.cleanarchitecturedemo.domain.models.Fork

interface ForkUseCase {

    suspend fun getForksFromApi(): List<Fork>?

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): List<Fork>

    fun getForkById(forkId: Long): Fork?
}