package com.vnteam.architecturetemplates.presentation.states.screen

data class TopAppBarState(var topAppBarTitle: String = "",
                          var topAppBarActionVisible: Boolean = false,
                          var topAppBarAction: () -> Unit = {})
