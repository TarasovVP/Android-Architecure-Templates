package com.vnteam.architecturetemplates.presentation.states.screen

data class SnackBarState(var snackbarVisible: Boolean = false,
                         var isSnackbarError: Boolean = false,
                         var snackbarMessage: String = "")
