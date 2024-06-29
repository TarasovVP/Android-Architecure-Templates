package com.vnteam.architecturetemplates.presentation.usecaseimpl

import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase

class AppUseCaseImpl(private val preferencesRepository: PreferencesRepository) :
    AppUseCase {
    override fun setIsDarkTheme(isDarkTheme: Boolean) {
        preferencesRepository.setIsDarkTheme(isDarkTheme)
    }

    override fun getIsDarkTheme(): Boolean {
        return preferencesRepository.getIsDarkTheme()
    }

    override fun setLanguage(language: String) {
        preferencesRepository.setLanguage(language)
    }

    override fun getLanguage(): String? {
        return preferencesRepository.getLanguage()
    }

}