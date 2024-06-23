package presentation

data class ScreenState(var topAppBarTitle: String = "",
                       var topAppBarActionVisible: Boolean = false,
                       var topAppBarAction: () -> Unit = {},
                       var snackbarVisible: Boolean = false,
                       var isSnackbarError: Boolean = false,
                       var snackbarMessage: String = "",
                       var isProgressVisible: Boolean = false,
                       var isDarkTheme: Boolean = false,
                       var currentLanguage: String = "EN"
)
