package com.vnteam.architecturetemplates

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import presentation.App
import presentation.setActivityProvider
import theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActivityProvider { this }
        setContent {
            AppTheme {
                App()
            }
        }
    }
}