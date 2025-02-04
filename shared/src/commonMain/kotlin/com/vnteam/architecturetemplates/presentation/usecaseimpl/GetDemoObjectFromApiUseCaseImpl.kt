package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectFromApiUseCase
import kotlinx.coroutines.flow.Flow

class GetDemoObjectFromApiUseCaseImpl(
    private val apiRepository: ApiRepository
) : GetDemoObjectFromApiUseCase {

    override suspend fun execute(params: String): Flow<DemoObject?> {
        return apiRepository.getDemoObjectById(params)
    }
}