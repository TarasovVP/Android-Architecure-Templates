package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase

class FakeCreateDemoObjectUseCase : CreateDemoObjectUseCase {

    var demoObject: DemoObject? = null

    override suspend fun execute(params: DemoObject) {
        demoObject = params
    }
}