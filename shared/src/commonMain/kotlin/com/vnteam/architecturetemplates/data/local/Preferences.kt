package com.vnteam.architecturetemplates.data.local

interface Preferences {
    fun putString(key: String, value: String)
    fun getString(key: String): String?

    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String): Boolean
}