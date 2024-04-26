package com.vnteam.architecturetemplates

import androidx.compose.ui.window.ComposeUIViewController
import com.vnteam.architecturetemplates.presentation.AppNavigation
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {

    return ComposeUIViewController {
        AppNavigation()
    }
}