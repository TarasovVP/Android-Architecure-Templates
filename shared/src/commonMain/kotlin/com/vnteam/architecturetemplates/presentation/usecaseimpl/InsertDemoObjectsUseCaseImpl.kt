package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase
import kotlinx.coroutines.flow.Flow

class InsertDemoObjectsUseCaseImpl(
    private val dbRepository: DBRepository
) : InsertDemoObjectsUseCase {

    override suspend fun execute(params: List<DemoObject>): Flow<Unit> {
        return dbRepository.insertDemoObjectsToDB(params)
    }
}