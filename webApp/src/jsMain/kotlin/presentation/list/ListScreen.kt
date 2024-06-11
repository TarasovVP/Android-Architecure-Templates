package presentation.list
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import components.BaseButton
import components.CircularProgress
import components.DynamicVerticalList
import components.VerticalLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import kotlinx.browser.window
import org.w3c.dom.events.Event
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel


@Composable
fun ListScreen() {

    val viewModel = viewModel(ListViewModel::class)
    val forks = viewModel.state.collectAsState()

    VerticalLayout {
        BaseButton(getStringResources().START) {
            viewModel.processIntent(ListIntent.LoadForks(true))
        }
        DynamicVerticalList(forks.value.forks?.map { it.name.orEmpty() } ?: emptyList()) { itemName ->
            val item = forks.value.forks?.find { it.name == itemName }
            window.history.pushState(null, "", "/details/${item?.forkId}")
            window.dispatchEvent(Event("popstate"))
        }
        if (viewModel.state.value.isLoading) {
            CircularProgress()
        }
        viewModel.state.value.infoMessage.value.takeIf { it != null }?.let {
            Snackbar(snackbarData = object : SnackbarData {
                override val visuals: SnackbarVisuals
                    get() = object : SnackbarVisuals {
                        override val actionLabel: String = ""
                        override val duration: SnackbarDuration = SnackbarDuration.Short
                        override val message: String = it.message
                        override val withDismissAction: Boolean = true
                    }
                override fun dismiss() = Unit
                override fun performAction() = Unit
            }, containerColor = if (it.isError) Color.Red else Color.Green)
        }
    }
}