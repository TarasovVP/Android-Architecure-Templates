package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.DetailsUseCase
import kotlinx.coroutines.flow.Flow

class DetailsUseCaseImpl(private val dbRepository: DBRepository) :
    DetailsUseCase {

    override suspend fun getForkById(id: Long): Flow<Fork?> {
        return dbRepository.getForkById(id)
    }
}