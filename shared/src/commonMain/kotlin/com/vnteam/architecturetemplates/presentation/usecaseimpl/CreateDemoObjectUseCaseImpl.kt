package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import kotlinx.coroutines.flow.Flow

class CreateDemoObjectUseCaseImpl(
    private val apiRepository: ApiRepository
) : CreateDemoObjectUseCase {

    override suspend fun execute(params: DemoObject): Flow<Unit> {
        return apiRepository.insertDemoObjectsToApi(listOf(params))
    }
}