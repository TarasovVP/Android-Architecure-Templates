package com.vnteam.architecturetemplates.domain.repositories

interface PreferencesRepository {

    fun setIsDarkTheme(isDarkTheme: Boolean)

    fun getIsDarkTheme(): Boolean

    fun setLanguage(language: String)

    fun getLanguage(): String?
}