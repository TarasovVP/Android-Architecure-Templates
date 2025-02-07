package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeClearDemoObjectsUseCase : ClearDemoObjectUseCase {

    var isExecuteCalled = false

    override suspend fun execute(params: Nothing?): Flow<Unit> {
        isExecuteCalled = true
        return flowOf(Unit)
    }
}