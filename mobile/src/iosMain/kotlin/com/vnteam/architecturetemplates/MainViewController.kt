import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.koinInject
import platform.UIKit.UIViewController
import com.vnteam.architecturetemplates.presentation.App

fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App(koinInject())
    }
}