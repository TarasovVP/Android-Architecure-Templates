package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DBRepositoryImpl(private val forkDao: ForkDao, private val forkDBMapper: ForkDBMapper):
    DBRepository {

    override suspend fun clearForks() {
        forkDao.clearForks()

    }

    override suspend fun insertForksToDB(forks: List<Fork>): Flow<Unit> = flow {
        forkDao.insertForkWithOwners(forkDBMapper.mapToImplModelList(forks))
        emit(Unit)
    }

    override suspend fun getForksFromDB(): Flow<List<Fork>> =
        forkDao.getForkWithOwners().map { forkWithOwners ->
            forkDBMapper.mapFromImplModelList(forkWithOwners)
        }


    override suspend fun getForkById(forkId: String): Flow<Fork?> =
        forkDao.getForkById(forkId).map { forkWithOwner ->
            forkWithOwner?.let { forkDBMapper.mapFromImplModel(it) }
        }

    override suspend fun deleteForkById(forkId: String): Flow<Unit> = flow {
        forkDao.deleteForkById(forkId)
        emit(Unit)
    }
}