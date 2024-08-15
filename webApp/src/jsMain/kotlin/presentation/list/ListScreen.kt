package presentation.list

import components.BaseButton
import components.DynamicVerticalList
import components.VerticalLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import kotlinx.browser.window
import org.w3c.dom.events.Event
import presentation.viewModel


@Composable
fun ListScreen() {

    val viewModel = viewModel(ListViewModel::class)
    val forks = viewModel.state.collectAsState()

    VerticalLayout {
        BaseButton(LocalStringResources.current.START) {
            viewModel.processIntent(ListIntent.LoadForks(true))
        }
        DynamicVerticalList(forks.value.forks?.map { it.name.orEmpty() }
            ?: emptyList()) { itemName ->
            val item = forks.value.forks?.find { it.name == itemName }
            window.history.pushState(null, "", "/details/${item?.forkId}")
            window.dispatchEvent(Event("popstate"))
        }
    }
}