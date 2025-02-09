package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectUseCase
import kotlinx.coroutines.flow.flowOf

class FakeGetDemoObjectUseCase : GetDemoObjectUseCase {

    var demoObject: DemoObject? = null

    override suspend fun execute(params: String) =
        flowOf(demoObject)
}