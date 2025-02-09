package com.vnteam.architecturetemplates.domain.usecase

import kotlinx.coroutines.flow.Flow

interface DeleteDemoObjectUseCase : UseCase<String, Flow<Unit>>