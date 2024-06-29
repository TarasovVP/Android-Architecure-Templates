package com.vnteam.architecturetemplates.domain.usecase

interface AppUseCase {

    fun setIsDarkTheme(isDarkTheme: Boolean)

    fun getIsDarkTheme(): Boolean

    fun setLanguage(language: String)

    fun getLanguage(): String?
}