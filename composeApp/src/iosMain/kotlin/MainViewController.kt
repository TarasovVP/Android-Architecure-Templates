import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import theme.AppTheme
import com.vnteam.architecturetemplates.presentation.viewmodels.withViewModelStoreOwner
import presentation.App

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        withViewModelStoreOwner {
            AppTheme {
                App()
                println("isNetworkAvailable: ${isNetworkAvailable()}")
            }
        }
    }
}