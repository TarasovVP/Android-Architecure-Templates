package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.DemoObject
import kotlinx.coroutines.flow.Flow

interface CreateDemoObjectUseCase : UseCase<DemoObject, Flow<Unit>>