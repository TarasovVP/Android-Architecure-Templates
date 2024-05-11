
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import kotlinx.browser.window
import org.koin.compose.koinInject
import org.w3c.dom.events.Event
import presentation.list.ListViewModel


@Composable
fun ListScreen() {

    val viewModel = koinInject<ListViewModel>()
    val forks = viewModel.state.collectAsState()
    val error = mutableStateOf( viewModel.state.value.error )

    LaunchedEffect(forks.value) {
        error.value = viewModel.state.value.error
    }

    VerticalLayout {
        StartButton {
            viewModel.processIntent(ListIntent.LoadForks())
        }
        DynamicVerticalList(forks.value.forks?.map { it.fullName } ?: emptyList()) { itemId ->
            window.history.pushState(null, "", "/details/$itemId")
            window.dispatchEvent(Event("popstate"))
        }
        if (error.value.isNullOrEmpty().not()) {
            Toast(error.value.orEmpty()) {
                error.value = null
            }
        }
        if (viewModel.state.value.isLoading) {
            CircularProgress()
        }
    }
}