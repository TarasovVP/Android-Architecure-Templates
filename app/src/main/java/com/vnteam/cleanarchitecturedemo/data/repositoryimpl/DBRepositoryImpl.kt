package com.vnteam.cleanarchitecturedemo.data.repositoryimpl

import com.vnteam.cleanarchitecturedemo.data.database.ForkDao
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.repositories.DBRepository

class DBRepositoryImpl(private val forkDao: ForkDao, private val forkDBMapper: ForkDBMapper):
    DBRepository {

    override fun insertForksToDB(forks: List<Fork>) {
        forkDao.insertForks(forkDBMapper.mapToImplModelList(forks))
    }

    override fun getForksFromDB(): List<Fork> {
        return forkDBMapper.mapFromImplModelList(forkDao.getForks())
    }

    override fun getForkById(forkId: Long): Fork {
        return forkDBMapper.mapFromImplModel(forkDao.getForkById(forkId))
    }
}