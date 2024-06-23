package com.vnteam.architecturetemplates.data.local

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory {
    actual fun createPreferences(): Settings {
        val preferences = Preferences.userRoot()
        return PreferencesSettings(preferences)
    }
}