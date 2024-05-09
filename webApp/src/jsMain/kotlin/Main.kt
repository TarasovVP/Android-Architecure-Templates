
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
import org.w3c.dom.events.Event
import presentation.list.ListViewModel



fun main() {
    doInitKoin()
    console.log("Hello from Compose for Web!")
    renderComposable(rootElementId = "content-root") {
        console.log("content-root")
        val viewModel = koinInject<ListViewModel>()
        val forks = viewModel.state.collectAsState()
        val desktop2Btn = document.querySelector(".start-btn") as? HTMLElement
        desktop2Btn?.addEventListener("click", { event: Event ->
            desktop2Btn.textContent = "Button clicked!"
            console.log("Button clicked!")
            viewModel.processIntent(ListIntent.LoadForks())
        })
        val userList = document.getElementById("userList") as? HTMLElement
        userList?.innerHTML = ""
        forks.value.forks?.forEach { fork ->
            console.log(fork.fullName)
            desktop2Btn?.textContent = fork.fullName.orEmpty()
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
    }
    /*renderComposable(rootElementId = "desktop2-container") {
        val viewModel = koinInject<ListViewModel>()
        val forks = viewModel.state.collectAsState()
        val desktop2Btn = document.getElementById("desktop2-btn") as? HTMLElement
        desktop2Btn?.addEventListener("click", { event: Event ->
            desktop2Btn.textContent = "Button clicked!"
            console.log("Button clicked!")
            viewModel.processIntent(ListIntent.LoadForks())
        })

        val userList = document.getElementById("userList") as? HTMLElement
        userList?.innerHTML = ""
        forks.value.forks?.forEach { fork ->
            console.log(fork.fullName)
            desktop2Btn?.textContent = fork.fullName.orEmpty()
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
    }*/
}