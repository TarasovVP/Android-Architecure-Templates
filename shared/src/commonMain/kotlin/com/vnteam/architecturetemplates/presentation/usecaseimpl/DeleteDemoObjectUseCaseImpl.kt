package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.DeleteDemoObjectUseCase
import kotlinx.coroutines.flow.Flow

class DeleteDemoObjectUseCaseImpl(
    private val dbRepository: DBRepository
) : DeleteDemoObjectUseCase {

    override suspend fun execute(params: String): Flow<Unit> {
        return dbRepository.deleteDemoObjectById(params)
    }
}