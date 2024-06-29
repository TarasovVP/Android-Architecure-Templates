package com.vnteam.architecturetemplates.data.local

import platform.Foundation.NSUserDefaults

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PreferencesFactory : Preferences {

    private val userDefaults: NSUserDefaults = NSUserDefaults.standardUserDefaults

    override fun putString(key: String, value: String) {
        userDefaults.setObject(value, forKey = key)
    }

    override fun getString(key: String): String? {
        return userDefaults.stringForKey(key)
    }

    override fun putBoolean(key: String, value: Boolean) {
        userDefaults.setBool(value, forKey = key)
    }

    override fun getBoolean(key: String): Boolean {
        return userDefaults.boolForKey(key)
    }
}