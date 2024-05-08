
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import di.doInitKoin
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import kotlinx.browser.document
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.alignItems
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable
import org.koin.compose.koinInject
import org.koin.dsl.koinApplication
import org.w3c.dom.HTMLElement
import presentation.list.ListViewModel

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    doInitKoin()
    println("Hello from Compose for Web!")
    renderComposable(rootElementId = "desktop2-container") {
        val viewModel = koinInject<ListViewModel>()
        viewModel.processIntent(ListIntent.LoadForks())
        val forks = viewModel.state.collectAsState()
        val userList = document.getElementById("userList") as? HTMLElement
        userList?.innerHTML = ""
        forks.value.forks?.forEach { fork ->
            userList?.append(Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        alignItems(AlignItems.Center)
                    }
                }
            ) {

                Span {
                    Text(fork.fullName.orEmpty())
                }
            })

        }
       /* val listViewModel = koinApplication().koin.get<ListViewModel>()
        listViewModel.processIntent(ListIntent.LoadForks())

        val forks = listViewModel.state.collectAsState()

        LaunchedEffect(forks.value) {
            println(forks.value.forks)
        }*/

    }
}