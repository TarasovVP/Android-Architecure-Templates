
import androidx.compose.runtime.Composable
import platform.UIKit.UIViewController
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.vnteam.architecturetemplates.PlatformMessageDisplayer
import org.koin.compose.koinInject

@Composable
fun MainViewController(): UIViewController {
    val platformMessageDisplayer: PlatformMessageDisplayer = koinInject()
    val viewController = ComposeUIViewController {
        Navigator(NavigationScreen.ListContentScreen())
    }
    platformMessageDisplayer.setUIViewController(viewController)

    return viewController
}