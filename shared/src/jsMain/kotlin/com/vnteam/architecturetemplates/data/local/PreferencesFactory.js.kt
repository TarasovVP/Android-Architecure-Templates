package com.vnteam.architecturetemplates.data.local

import kotlinx.browser.localStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    actual override suspend fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    actual override suspend fun getString(key: String): Flow<String?> {
        return flowOf( localStorage.getItem(key) )
    }

    actual override suspend fun putBoolean(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    actual override suspend fun getBoolean(key: String): Flow<Boolean> {
        return flowOf( localStorage.getItem(key)?.toBoolean() ?: false)
    }
}