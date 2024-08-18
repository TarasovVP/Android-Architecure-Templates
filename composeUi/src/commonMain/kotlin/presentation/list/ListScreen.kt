package presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import org.koin.compose.koinInject

@Composable
fun ListScreen(screenState: MutableState<ScreenState> = mutableStateOf(ScreenState()),
               onItemClick: (ForkUI) -> Unit,
               content: @Composable (State<ListViewState>, onItemClick: (ForkUI, String) -> Unit) -> Unit) {
    val listViewModel = koinInject<ListViewModel>()
    val viewModel = androidx.lifecycle.viewmodel.compose.viewModel { listViewModel }
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewState.value.takeIf { it.forks == null }?.let {
            viewModel.processIntent(ListIntent.ClearForks())
        }
    }

    LaunchedEffect(viewState.value.successResult) {
        if (viewState.value.successResult){
            viewModel.processIntent(ListIntent.ClearForks())
        }
    }

    LaunchedEffect(screenState.value.isScreenUpdatingNeeded) {
        if (screenState.value.isScreenUpdatingNeeded && viewState.value.forks != null) {
            viewModel.processIntent(ListIntent.LoadForks(true))
            screenState.value = screenState.value.copy(isScreenUpdatingNeeded = false)
        }
    }

    content(viewState) { forkUI, action ->
        when (action) {
            "refresh" -> viewModel.processIntent(ListIntent.LoadForks(false))
            "details" -> onItemClick(forkUI)
            "confirm_delete" -> {
                viewState.value.isConfirmationDialogVisible.value = true
                viewState.value.forkToDelete = forkUI.forkId.orEmpty()
            }
            "delete" -> {
                viewState.value.isConfirmationDialogVisible.value = false
                viewState.value.forkToDelete = ""
                viewModel.processIntent(ListIntent.DeleteFork( forkUI.forkId.orEmpty() ))
            }
        }
    }
}