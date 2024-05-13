package presentation.list
import components.BaseButton
import components.CircularProgress
import components.DynamicVerticalList
import components.Toast
import components.VerticalLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import kotlinx.browser.window
import org.koin.compose.koinInject
import org.w3c.dom.events.Event
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewModel


@Composable
fun ListScreen() {

    val viewModel = viewModel(ListViewModel::class)
    val forks = viewModel.state.collectAsState()
    val error = mutableStateOf( viewModel.state.value.error )

    LaunchedEffect(forks.value) {
        error.value = viewModel.state.value.error
    }

    VerticalLayout {
        BaseButton(getStringResources().START) {
            viewModel.processIntent(ListIntent.LoadForks())
        }
        DynamicVerticalList(forks.value.forks?.map { it.fullName } ?: emptyList()) { itemName ->
            val item = forks.value.forks?.find { it.fullName == itemName }
            window.history.pushState(null, "", "/details/${item?.id}")
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