package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.DeleteDemoObjectUseCase

class FakeDeleteDemoObjectUseCase : DeleteDemoObjectUseCase {

    var isExecuteCalled = false

    override suspend fun execute(params: String) {
        isExecuteCalled = true
    }
}