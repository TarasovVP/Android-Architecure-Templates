package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromApiUseCase
import kotlinx.coroutines.flow.Flow

class GetDemoObjectsFromApiUseCaseImpl(
    private val apiRepository: ApiRepository
) : GetDemoObjectsFromApiUseCase {

    override suspend fun execute(params: Nothing?): Flow<List<DemoObject>?> {
        return apiRepository.getDemoObjectsFromApi()
    }
}