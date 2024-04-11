package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DBRepositoryImpl(private val forkDao: ForkDao, private val forkDBMapper: ForkDBMapper):
    DBRepository {

    override fun insertForksToDB(forks: List<Fork>) {
        forkDao.insertForkWithOwners(forkDBMapper.mapToImplModelList(forks))
    }

    override fun getForksFromDB(): Flow<List<Fork>> {
        return flowOf( forkDBMapper.mapFromImplModelList(forkDao.getForks()) )
    }

    override fun getForkById(forkId: Long): Flow<Fork?> {
        return flowOf( forkDao.getForkById(forkId)?.let { forkDBMapper.mapFromImplModel(it) } )
    }
}