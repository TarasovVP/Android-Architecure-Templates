package com.vnstudio.cleanarchitecturedemo.database

import com.vnstudio.cleanarchitecturedemo.models.Fork

class ForkRepository(private val forkDao: ForkDao) {

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