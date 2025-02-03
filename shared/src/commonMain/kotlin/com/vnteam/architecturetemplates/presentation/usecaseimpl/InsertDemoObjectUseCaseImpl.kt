package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectUseCase
import kotlinx.coroutines.flow.Flow

class InsertDemoObjectUseCaseImpl(
    private val dbRepository: DBRepository
) : InsertDemoObjectUseCase {

    override suspend fun execute(params: DemoObject): Flow<Unit> {
        return dbRepository.insertDemoObjectsToDB(listOf(params))
    }
}