package com.vnteam.architecturetemplates.data.local

import com.russhwolf.settings.Settings
import com.russhwolf.settings.StorageSettings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory {
    actual fun createPreferences(): Settings {
        return StorageSettings()
    }
}