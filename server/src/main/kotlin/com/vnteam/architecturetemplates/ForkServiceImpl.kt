package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.firstOrNull

class ForkServiceImpl(private val forkDao: ForkDao, private val forkDBMapper: ForkDBMapper):
    ForkService {

    override suspend fun insertForks(forks: List<Fork>) {
        forkDao.insertForkWithOwners(forkDBMapper.mapToImplModelList(forks))
    }

    override suspend fun getForks(): List<Fork>? = forkDao.getForkWithOwners().firstOrNull()
        ?.let { forkDBMapper.mapFromImplModelList(it) }


    override suspend fun getForkById(forkId: String): Fork? =
        forkDao.getForkById(forkId).firstOrNull()?.let { forkDBMapper.mapFromImplModel(it) }

    override suspend fun deleteForkById(forkId: String) {
        forkDao.deleteForkById(forkId)
    }
}