package com.vnteam.architecturetemplates.fake.data.repositoryimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.fake.domain.models.fakeException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiRepository : ApiRepository {

    var isSuccessResult = true
    var isInsertDemoObjectsToApiCalled = false
    var isDeleteDemoObjectByIdCalled = false
    var demoObject: DemoObject? = null
    var demoObjects: List<DemoObject>? = null

    override suspend fun getDemoObjectsFromApi(): Flow<List<DemoObject>> {
        if (isSuccessResult) {
            return flowOf(demoObjects.orEmpty())
        } else {
            throw fakeException
        }
    }

    override suspend fun insertDemoObjectsToApi(demoObjects: List<DemoObject>?): Flow<Unit> {
        if (isSuccessResult) {
            isInsertDemoObjectsToApiCalled = true
            return flowOf(Unit)
        } else {
            throw fakeException
        }
    }

    override suspend fun getDemoObjectById(demoObjectId: String?): Flow<DemoObject?> {
        if (isSuccessResult) {
            return flowOf(demoObject)
        } else {
            throw fakeException
        }
    }

    override suspend fun deleteDemoObjectById(demoObjectId: String): Flow<Unit> {
        if (isSuccessResult) {
            isDeleteDemoObjectByIdCalled = true
            return flowOf(Unit)
        } else {
            throw fakeException
        }
    }
}