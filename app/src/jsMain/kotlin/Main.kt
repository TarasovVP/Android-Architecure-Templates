import androidx.compose.runtime.*
import com.vnteam.architecturetemplates.presentation.AppNavigation
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    doInitKoin()

    renderComposable(rootElementId = "root") {
        AppNavigation()
    }
}