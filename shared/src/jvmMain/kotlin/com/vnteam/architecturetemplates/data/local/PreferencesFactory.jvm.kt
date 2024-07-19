package com.vnteam.architecturetemplates.data.local

import java.util.prefs.Preferences.userRoot

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    private val prefs: java.util.prefs.Preferences = userRoot().node(this::class.java.name)

    override suspend fun putString(key: String, value: String) {
        prefs.put(key, value)
    }

    override suspend fun getString(key: String): String? {
        return prefs.get(key, null)
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        prefs.putBoolean(key, value)
    }

    override suspend fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }
}