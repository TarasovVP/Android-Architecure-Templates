package com.vnteam.architecturetemplates.domain.usecase

import com.vnteam.architecturetemplates.domain.models.DemoObject
import kotlinx.coroutines.flow.Flow

interface GetDemoObjectFromDBUseCase : UseCase<String, Flow<DemoObject?>>