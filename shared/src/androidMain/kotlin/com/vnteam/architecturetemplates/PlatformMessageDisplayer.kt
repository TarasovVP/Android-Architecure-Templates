package com.vnteam.architecturetemplates

import android.content.Context
import android.widget.Toast

actual class PlatformMessageDisplayer(context: Context) {
    private val appContext = context.applicationContext
    actual fun showPopupMessage(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }
}