package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromDBUseCase
import kotlinx.coroutines.flow.flowOf

class FakeGetDemoObjectsFromDBUseCase : GetDemoObjectsFromDBUseCase {

    var demoObjects: List<DemoObject>? = null

    override suspend fun execute(params: Nothing?) =
        flowOf(demoObjects)
}