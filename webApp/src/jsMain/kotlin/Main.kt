import com.vnteam.architecturetemplates.presentation.withViewModelStoreOwner
import di.doInitKoin
import kotlinx.browser.window
import org.jetbrains.compose.web.renderComposable


fun main() {
    doInitKoin()
    renderComposable(rootElementId = "content-root") {
        if (window.location.pathname == "/" || window.location.pathname.isBlank()) {
            window.history.pushState(null, "", "/list")
        }
        withViewModelStoreOwner {
            AppContent()
        }
    }
}