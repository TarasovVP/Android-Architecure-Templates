package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectUseCase
import kotlinx.coroutines.flow.firstOrNull

class GetDemoObjectUseCaseImpl(
    private val apiRepository: ApiRepository,
    private val dbRepository: DBRepository,
) : GetDemoObjectUseCase {
    override suspend fun execute(params: String): DemoObject? {
        return dbRepository.getDemoObjectById(params).firstOrNull()
            ?: apiRepository.getDemoObjectById(params)
    }
}
