package com.vnteam.architecturetemplates.data.local

import kotlinx.browser.localStorage

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    override suspend fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    override suspend fun getString(key: String): String? {
        return localStorage.getItem(key)
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    override suspend fun getBoolean(key: String): Boolean {
        return localStorage.getItem(key)?.toBoolean() ?: false
    }
}