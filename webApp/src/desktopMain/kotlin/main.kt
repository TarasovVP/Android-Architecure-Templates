
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vnteam.architecturetemplates.di.doInitKoin
import presentation.AppNavigation
import resources.getStringResources
import theme.AppTheme

fun main() {
    doInitKoin()
    application {
        Window(onCloseRequest = ::exitApplication, title = getStringResources().APP_NAME) {
            AppTheme {
                AppNavigation()
            }
        }
    }
}