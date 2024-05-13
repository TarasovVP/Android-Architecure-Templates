import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import presentation.AppNavigation
import theme.AppTheme
import com.vnteam.architecturetemplates.presentation.withViewModelStoreOwner

fun MainViewController(): UIViewController {

    return ComposeUIViewController {
        withViewModelStoreOwner {
            AppTheme {
                AppNavigation()
            }
        }
    }
}