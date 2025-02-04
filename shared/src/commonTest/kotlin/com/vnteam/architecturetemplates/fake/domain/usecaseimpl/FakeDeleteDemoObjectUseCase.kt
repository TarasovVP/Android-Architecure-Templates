package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.DeleteDemoObjectUseCase
import kotlinx.coroutines.flow.flowOf

class FakeDeleteDemoObjectUseCase : DeleteDemoObjectUseCase {

    override suspend fun execute(params: String) = flowOf(Unit)
}