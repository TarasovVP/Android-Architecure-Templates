package com.vnteam.architecturetemplates.data.local

import kotlinx.browser.localStorage

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    override fun putString(key: String, value: String) {
        localStorage.setItem(key, value)
    }

    override fun getString(key: String): String? {
        return localStorage.getItem(key)
    }

    override fun putBoolean(key: String, value: Boolean) {
        localStorage.setItem(key, value.toString())
    }

    override fun getBoolean(key: String): Boolean {
        return localStorage.getItem(key)?.toBoolean() ?: false
    }
}