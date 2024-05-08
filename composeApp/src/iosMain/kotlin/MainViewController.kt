import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import presentation.AppNavigation
import theme.AppTheme

fun MainViewController(): UIViewController {

    return ComposeUIViewController {
        AppTheme {
            AppNavigation()
        }
    }
}