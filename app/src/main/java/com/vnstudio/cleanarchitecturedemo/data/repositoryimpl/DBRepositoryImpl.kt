package com.vnstudio.cleanarchitecturedemo.data.repositoryimpl

import com.vnstudio.cleanarchitecturedemo.data.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository

class DBRepositoryImpl(private val forkDao: ForkDao):
    DBRepository {

    override fun insertForksToDB(forks: List<Fork>) {
        forkDao.insertForks(forks)
    }

    override fun getForksFromDB(): List<Fork> {
        return forkDao.getForks()
    }

    override fun getForkById(forkId: Long): Fork {
        return forkDao.getForkById(forkId)
    }
}