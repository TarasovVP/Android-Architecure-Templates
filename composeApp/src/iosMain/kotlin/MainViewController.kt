import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import theme.AppTheme
import presentation.App

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        AppTheme {
            App()
        }
    }
}