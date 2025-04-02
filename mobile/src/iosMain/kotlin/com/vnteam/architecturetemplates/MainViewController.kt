import androidx.compose.ui.window.ComposeUIViewController
import com.vnteam.architecturetemplates.presentation.App
import org.koin.compose.koinInject
import platform.UIKit.UIViewController

// ktlint-disable
fun MainViewController(): UIViewController {
    return ComposeUIViewController {
        App(koinInject())
    }
}
