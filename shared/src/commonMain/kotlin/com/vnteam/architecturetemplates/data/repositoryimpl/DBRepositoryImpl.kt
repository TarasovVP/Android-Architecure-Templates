package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.database.DemoObject
import com.vnteam.architecturetemplates.domain.mappers.DemoObjectDBMapper
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DBRepositoryImpl(private val demoObject: DemoObject, private val demoObjectDBMapper: DemoObjectDBMapper):
    DBRepository {

    override fun insertDemoObjectsToDB(demoObjects: List<com.vnteam.architecturetemplates.domain.models.DemoObject>) {
        demoObject.insertDemoObjectWithOwners(demoObjectDBMapper.mapToImplModelList(demoObjects))
    }

    override fun getDemoObjectsFromDB(): Flow<List<com.vnteam.architecturetemplates.domain.models.DemoObject>> {
        return flowOf( demoObjectDBMapper.mapFromImplModelList(demoObject.getDemoObjects()) )
    }

    override fun getDemoObjectById(demoObjectId: Long): Flow<com.vnteam.architecturetemplates.domain.models.DemoObject?> {
        return flowOf( demoObject.getDemoObjectById(demoObjectId)?.let { demoObjectDBMapper.mapFromImplModel(it) } )
    }
}