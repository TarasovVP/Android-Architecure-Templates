package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.DeleteDemoObjectUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDeleteDemoObjectUseCase : DeleteDemoObjectUseCase {

    var isExecuteCalled = false

    override suspend fun execute(params: String): Flow<Unit> {
        isExecuteCalled = true
        return flowOf(Unit)
    }
}