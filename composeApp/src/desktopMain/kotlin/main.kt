
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vnteam.architecturetemplates.di_desktop.doInitKoin
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import presentation.AppNavigation
import com.vnteam.architecturetemplates.presentation.withViewModelStoreOwner
import theme.AppTheme

fun main() {
    doInitKoin()
    application {
        Window(onCloseRequest = ::exitApplication, title = getStringResources().APP_NAME) {
            withViewModelStoreOwner {
                AppTheme {
                    AppNavigation()
                }
            }
        }
    }
}