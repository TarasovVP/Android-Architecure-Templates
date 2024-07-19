package com.vnteam.architecturetemplates.data.local

import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    private val defaults = NSUserDefaults.standardUserDefaults

    override suspend fun putString(key: String, value: String) {
        defaults.setObject(value, forKey = key)
    }

    override suspend fun getString(key: String): String? {
        return defaults.stringForKey(key)
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        defaults.setBool(value, forKey = key)
    }

    override suspend fun getBoolean(key: String): Boolean {
        return defaults.boolForKey(key).takeIf { defaults.objectForKey(key) != null } == true
    }
}