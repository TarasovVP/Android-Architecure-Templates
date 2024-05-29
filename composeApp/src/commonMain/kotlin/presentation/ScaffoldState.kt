package presentation

data class ScaffoldState(var topAppBarTitle: String = "",
                         var topAppBarActionVisible: Boolean = false,
                         var topAppBarAction: () -> Unit = {},
                         var floatingActionButtonVisible: Boolean = false,
                         var floatingActionButtonTitle: String = "",
                         var floatingActionButtonAction: () -> Unit = {}
)
