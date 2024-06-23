import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import presentation.App

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}