package presentation

data class ScreenState(var topAppBarTitle: String = "",
                       var topAppBarActionVisible: Boolean = false,
                       var topAppBarAction: () -> Unit = {},
                       var floatingActionButtonVisible: Boolean = false,
                       var floatingActionButtonTitle: String = "",
                       var floatingActionButtonAction: () -> Unit = {},
                       var snackbarVisible: Boolean = false,
                       var isSnackbarError: Boolean = false,
                       var snackbarMessage: String = "",
                       var isProgressVisible: Boolean = false
)
