import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController
import presentation.AppNavigation
import theme.AppTheme
import com.vnteam.architecturetemplates.presentation.viewmodels.withViewModelStoreOwner

fun MainViewController(): UIViewController {

    return ComposeUIViewController {
        withViewModelStoreOwner {
            AppTheme {
                AppNavigation()
            }
        }
    }
}