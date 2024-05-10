
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import di.doInitKoin
import org.jetbrains.compose.web.renderComposable
import org.koin.compose.koinInject
import presentation.list.ListViewModel


fun main() {
    doInitKoin()
    renderComposable(rootElementId = "content-root") {

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
            DynamicVerticalList(forks.value.forks?.map { it.fullName } ?: emptyList()) {
                error.value = "item clicked $it"
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
}