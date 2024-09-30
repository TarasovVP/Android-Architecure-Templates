package presentation.list

import components.BaseButton
import components.CircularProgress
import components.DynamicVerticalList
import components.VerticalLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import kotlinx.browser.window
import org.w3c.dom.events.Event
import presentation.viewModel


@Composable
fun ListScreen(screenState: ScreenState) {

    val viewModel = viewModel(ListViewModel::class)
    val demoObjects = viewModel.state.collectAsState()

    VerticalLayout {
        BaseButton("Load") {
            viewModel.processIntent(ListIntent.LoadForks(true))
        }
        DynamicVerticalList(demoObjects.value.forks?.map { it.name.orEmpty() }.orEmpty()) { itemName ->
            val item = demoObjects.value.forks?.find { it.name == itemName }
            window.history.pushState(null, "", "/details/${item?.forkId}")
            window.dispatchEvent(Event("popstate"))
        }
        if (screenState.isProgressVisible) {
            CircularProgress()
        }
    }
}