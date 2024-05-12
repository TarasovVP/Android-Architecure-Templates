
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.vnteam.architecturetemplates.di.appModule
import di_desktop.desktopModule
import org.koin.core.context.startKoin
import presentation.AppNavigation
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import theme.AppTheme

fun main() {
    startKoin {
        modules(appModule, desktopModule)
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = getStringResources().APP_NAME) {
            AppTheme {
                AppNavigation()
            }
        }
    }
}