package com.vnteam.architecturetemplates.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ClearDemoObjectUseCase : UseCase<Nothing?, Flow<Unit>>