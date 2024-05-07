
import com.vnteam.architecturetemplates.di.doInitKoin
import com.vnteam.architecturetemplates.presentation.details.DetailsViewModel
import org.jetbrains.compose.web.renderComposable
import org.koin.core.context.GlobalContext.get

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    doInitKoin()
    renderComposable(rootElementId = "home-section") {

    }
}