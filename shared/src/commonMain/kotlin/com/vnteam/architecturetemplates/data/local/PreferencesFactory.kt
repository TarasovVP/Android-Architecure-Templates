package com.vnteam.architecturetemplates.data.local

import com.russhwolf.settings.Settings

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class PreferencesFactory {
    fun createPreferences(): Settings
}