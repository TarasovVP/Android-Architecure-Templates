package presentation.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.data.generateUUID
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.resources.DrawableResources
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import com.vnteam.architecturetemplates.presentation.viewmodels.CreateViewModel
import org.koin.compose.koinInject
import presentation.components.ChangeAvatarDialog

@Composable
fun CreateScreen(forkId: String, screenState: MutableState<ScreenState>, content: @Composable (State<CreateViewState>,
                                                                                                                   originFork: MutableState<ForkUI?>,
                                                                                                                   onClick: () -> Unit) -> Unit) {
    val createViewModel = koinInject<CreateViewModel>()
    val viewModel = androidx.lifecycle.viewmodel.compose.viewModel { createViewModel }
    val viewState = viewModel.state.collectAsState()
    val originFork = mutableStateOf<ForkUI?>(null)

    LaunchedEffect(Unit) {
        if (forkId.isNotEmpty()) {
            viewModel.processIntent(CreateIntent.LoadFork(forkId))
        } else {
            viewModel.state.value.fork = mutableStateOf(
                ForkUI(
                    forkId = generateUUID(),
                    owner = OwnerUI(ownerId = generateUUID())
                )
            )
        }
    }
    LaunchedEffect(viewState.value) {
        originFork.value = viewState.value.fork.value
    }
    LaunchedEffect(viewState.value.successResult) {
        if (viewState.value.successResult) {
            screenState.value = screenState.value.copy(isScreenUpdatingNeeded = true)
            screenState.value.topAppBarState.topAppBarAction.invoke()
        }
    }

    viewState.value.fork.value?.let {
        content(viewState, originFork) {
            viewModel.processIntent(CreateIntent.CreateFork(it))
        }
    }
    screenState.value = screenState.value.copy()
    if (viewState.value.isChangeAvatarDialogVisible.value) {
        ChangeAvatarDialog(avatarList = DrawableResources.avatarList, onDismiss = {
            viewState.value.isChangeAvatarDialogVisible.value = false
        }, onClick = { avatar ->
            viewState.value.fork.value = viewState.value.fork.value?.copy(
                owner = viewState.value.fork.value?.owner?.copy(avatarUrl = avatar)
            )
            viewState.value.isChangeAvatarDialogVisible.value = false
        })
    }
}