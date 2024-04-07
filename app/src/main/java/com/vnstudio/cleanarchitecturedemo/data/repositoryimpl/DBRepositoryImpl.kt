package com.vnstudio.cleanarchitecturedemo.data.repositoryimpl

import com.vnstudio.cleanarchitecturedemo.data.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository

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