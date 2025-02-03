package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.LanguageUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeLanguageUseCase : LanguageUseCase {
    private var language: String? = ""

    override suspend fun set(params: String) {
        language = params
    }

    override fun get(): Flow<String?> {
        return flow {
            emit(language)
        }
    }
}