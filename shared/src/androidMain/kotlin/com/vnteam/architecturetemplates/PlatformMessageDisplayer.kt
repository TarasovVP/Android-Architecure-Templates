package com.vnteam.architecturetemplates

import android.content.Context
import android.widget.Toast

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformMessageDisplayer(context: Context) {
    private val appContext = context.applicationContext
    actual fun showPopupMessage(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_LONG).show()
    }
}