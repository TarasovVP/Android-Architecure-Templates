package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectFromDBUseCase
import kotlinx.coroutines.flow.Flow

class GetDemoObjectFromDBUseCaseImpl(
    private val dbRepository: DBRepository
) : GetDemoObjectFromDBUseCase {

    override suspend fun execute(params: String): Flow<DemoObject?> {
        return dbRepository.getDemoObjectById(params)
    }
}