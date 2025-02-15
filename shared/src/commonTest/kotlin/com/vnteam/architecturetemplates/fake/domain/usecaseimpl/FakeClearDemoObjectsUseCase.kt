package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase

class FakeClearDemoObjectsUseCase : ClearDemoObjectUseCase {

    var isExecuteCalled = false

    override suspend fun execute(params: Nothing?) {
        isExecuteCalled = true
    }
}