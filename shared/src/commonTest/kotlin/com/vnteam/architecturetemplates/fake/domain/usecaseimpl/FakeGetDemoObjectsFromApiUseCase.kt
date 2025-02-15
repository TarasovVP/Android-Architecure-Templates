package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromApiUseCase

class FakeGetDemoObjectsFromApiUseCase : GetDemoObjectsFromApiUseCase {

    var demoObjects: List<DemoObject>? = null

    override suspend fun execute(params: Nothing?) =
        demoObjects.orEmpty()
}