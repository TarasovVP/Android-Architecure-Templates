package com.vnteam.architecturetemplates.presentation.states.screen

data class AppBarState(var appBarTitle: String = "",
                       var topAppBarActionVisible: Boolean = false,
                       var topAppBarAction: () -> Unit = {})
