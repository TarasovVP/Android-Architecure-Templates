package com.vnteam.architecturetemplates

import androidx.compose.material.Text
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.vnteam.architecturetemplates.presentation.list.ListScreen
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    //TODO
    //val platformMessageDisplayer: PlatformMessageDisplayer = koinInject()
    /*val viewController = ComposeUIViewController {

        //ListScreen()
    }
    platformMessageDisplayer.setUIViewController(viewController)*/

    return ComposeUIViewController {
        Navigator(NavigationScreen.ListContentScreen())
    }
}