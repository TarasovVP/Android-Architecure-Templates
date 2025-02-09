package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCreateDemoObjectUseCase : CreateDemoObjectUseCase {

    var demoObject: DemoObject? = null

    override suspend fun execute(params: DemoObject): Flow<Unit> {
        demoObject = params
        println("testTAG FakeCreateDemoObjectUseCase execute demoObject $demoObject")
        return flowOf(Unit)
    }
}