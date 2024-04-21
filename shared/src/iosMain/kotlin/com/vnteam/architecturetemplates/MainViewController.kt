package com.vnteam.architecturetemplates

import androidx.compose.ui.window.ComposeUIViewController
import com.vnteam.architecturetemplates.presentation.AppNavigation
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    //TODO
    //val platformMessageDisplayer: PlatformMessageDisplayer = koinInject()
    /*val viewController = ComposeUIViewController {

        //ListScreen()
    }
    platformMessageDisplayer.setUIViewController(viewController)*/

    return ComposeUIViewController {
        AppNavigation()
    }
}