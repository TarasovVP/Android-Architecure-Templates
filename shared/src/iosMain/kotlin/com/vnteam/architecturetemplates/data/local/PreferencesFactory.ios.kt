package com.vnteam.architecturetemplates.data.local

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory {
    actual fun createPreferences(): Settings {
        val nSUserDefaults = NSUserDefaults()
        return NSUserDefaultsSettings(nSUserDefaults)
    }
}