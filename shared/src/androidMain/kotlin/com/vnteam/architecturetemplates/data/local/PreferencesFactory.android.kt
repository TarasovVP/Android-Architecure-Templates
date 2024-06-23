package com.vnteam.architecturetemplates.data.local

import android.preference.PreferenceManager
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory(private val context: android.content.Context) {
    actual fun createPreferences(): Settings {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        return SharedPreferencesSettings(sharedPrefs)
    }
}