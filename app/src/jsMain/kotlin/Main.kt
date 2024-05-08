
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.vnteam.architecturetemplates.di.doInitKoin
import com.vnteam.architecturetemplates.presentation.details.DetailsIntent
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import org.jetbrains.compose.web.renderComposable
import org.koin.dsl.koinApplication

@OptIn(ExperimentalJsExport::class)
@JsExport
fun main() {
    doInitKoin()
    println("Hello from Compose for Web!")
    renderComposable(rootElementId = "home-section") {
       /* val listViewModel = koinApplication().koin.get<ListViewModel>()
        listViewModel.processIntent(ListIntent.LoadForks())

        val forks = listViewModel.state.collectAsState()

        LaunchedEffect(forks.value) {
            println(forks.value.forks)
        }*/

    }
}