
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vnteam.architecturetemplates.di.doInitKoin
import com.vnteam.architecturetemplates.presentation.AppNavigation
import com.vnteam.architecturetemplates.resources.getStringResources
import com.vnteam.architecturetemplates.theme.AppTheme

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