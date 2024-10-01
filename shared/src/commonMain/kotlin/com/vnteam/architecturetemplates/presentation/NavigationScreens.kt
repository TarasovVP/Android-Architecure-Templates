package com.vnteam.architecturetemplates.presentation

import com.vnteam.architecturetemplates.presentation.Constants.CREATE_SCREEN
import com.vnteam.architecturetemplates.presentation.Constants.DETAILS_SCREEN
import com.vnteam.architecturetemplates.presentation.Constants.EDIT_SCREEN
import com.vnteam.architecturetemplates.presentation.Constants.MAIN_SCREEN

sealed class NavigationScreens(var route: String) {
    data object MainScreen : NavigationScreens("$MAIN_SCREEN/")
    data object DetailsScreen : NavigationScreens("$DETAILS_SCREEN/")
    data object EditScreen : NavigationScreens("$EDIT_SCREEN/")
    data object CreateScreen : NavigationScreens("$CREATE_SCREEN/")
}