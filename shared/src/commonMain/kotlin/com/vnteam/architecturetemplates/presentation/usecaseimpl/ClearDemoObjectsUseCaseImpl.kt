package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ClearDemoObjectsUseCaseImpl(
    private val dbRepository: DBRepository
) : ClearDemoObjectUseCase {

    override suspend fun execute(params: Nothing?): Flow<Unit> {
        dbRepository.clearDemoObjects()
        return flowOf(Unit)
    }
}