package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase

class FakeInsertDemoObjectsUseCase : InsertDemoObjectsUseCase {

    var demoObjects: List<DemoObject>? = null

    override suspend fun execute(params: List<DemoObject>) {
        demoObjects = params
    }
}