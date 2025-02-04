package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase
import kotlinx.coroutines.flow.flowOf

class FakeInsertDemoObjectsUseCase : InsertDemoObjectsUseCase {

    override suspend fun execute(params: List<DemoObject>) = flowOf(Unit)
}