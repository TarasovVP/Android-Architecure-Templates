package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase
import kotlinx.coroutines.flow.flowOf

class FakeClearDemoObjectsUseCase : ClearDemoObjectUseCase {

    override suspend fun execute(params: Nothing?) = flowOf(Unit)
}