package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.DetailsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

class DetailsUseCaseImpl(private val dbRepository: DBRepository, private val apiRepository: ApiRepository) :
    DetailsUseCase {

    override suspend fun getForkById(id: String): Flow<Fork?> {
        val dbFork = dbRepository.getForkById(id).firstOrNull()
        return if (dbFork != null) {
            flowOf(dbFork)
        } else {
            apiRepository.getForkById(id)
        }
    }
}