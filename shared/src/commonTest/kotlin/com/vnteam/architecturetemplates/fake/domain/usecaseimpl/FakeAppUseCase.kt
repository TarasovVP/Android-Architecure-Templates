package com.vnteam.architecturetemplates.fake.domain.usecaseimpl

import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import kotlinx.coroutines.flow.Flow

class FakeAppUseCase : AppUseCase {
    private var darkTheme: Boolean = false
    private var language: String = ""

    override suspend fun setIsDarkTheme(isDarkTheme: Boolean) {
        darkTheme = isDarkTheme
    }

    override suspend fun getIsDarkTheme(): Flow<Boolean> {
        return kotlinx.coroutines.flow.flow {
            emit(darkTheme)
        }
    }

    override suspend fun setLanguage(language: String) {
        this.language = language
    }

    override suspend fun getLanguage(): Flow<String?> {
        return kotlinx.coroutines.flow.flow {
            emit(language)
        }
    }
}