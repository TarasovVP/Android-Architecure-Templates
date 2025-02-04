package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import kotlinx.coroutines.flow.flowOf

class FakeCreateDemoObjectUseCase : CreateDemoObjectUseCase {

    override suspend fun execute(params: DemoObject) = flowOf(Unit)
}