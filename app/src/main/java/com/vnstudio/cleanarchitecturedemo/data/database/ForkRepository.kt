package com.vnstudio.cleanarchitecturedemo.data.database

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import com.vnstudio.cleanarchitecturedemo.data.network.ApiService

class ForkRepository(private val apiService: ApiService, private val forkDao: ForkDao) {

    suspend fun getForksFromApi(): List<Fork>? {
        return apiService.getForks().body()
    }

    fun insertForksToDB(forks: List<Fork>) {
        forkDao.insertForks(forks)
    }

    fun getForksFromDB(): List<Fork> {
        return forkDao.getForks()
    }

    fun getForkById(forkId: Long): Fork {
        return forkDao.getForkById(forkId)
    }
}