import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.vnteam.architecturetemplates.di.doInitKoin
import org.jetbrains.skiko.wasm.onWasmReady
import presentation.AppNavigation

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    doInitKoin()
    onWasmReady {
        CanvasBasedWindow("Architecture Templates") {
            AppNavigation()
        }
    }
}
