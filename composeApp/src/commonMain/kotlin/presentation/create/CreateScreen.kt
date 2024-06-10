package presentation.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.vnteam.architecturetemplates.data.database.generateUUID
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.resources.DrawableResources
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalSmallPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import com.vnteam.architecturetemplates.presentation.viewmodels.CreateViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import presentation.ScreenState
import presentation.components.AvatarImage
import presentation.components.ChangeAvatarDialog
import presentation.components.CommonTextField
import presentation.components.HeaderText

@Composable
fun CreateScreen(forkId: String, screenState: MutableState<ScreenState>) {
    val viewModel = viewModel(CreateViewModel::class)
    val viewState = viewModel.state.collectAsState()
    val originFork = mutableStateOf<ForkUI?>(null)

    LaunchedEffect(Unit) {
        if (forkId.isNotEmpty()) {
            viewModel.processIntent(CreateIntent.LoadFork(forkId))
        } else {
            viewModel.state.value.fork = mutableStateOf(
                ForkUI(
                    id = generateUUID(),
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
            screenState.value.topAppBarAction.invoke()
        }
    }
    LaunchedEffect(viewState.value.infoMessage.value) {
        viewState.value.infoMessage.value.takeIf { it != null }?.let {
            screenState.value = screenState.value.copy(
                snackbarVisible = true,
                snackbarMessage = it.message,
                isSnackbarError = it.isError
            )
        }
    }
    LaunchedEffect(viewState.value.isLoading) {
        screenState.value = screenState.value.copy(isProgressVisible = viewState.value.isLoading)
    }
    viewState.value.fork.value?.let {
        CreateContent(viewState, originFork) {
            viewModel.processIntent(CreateIntent.CreateFork(it))
        }
    }
    screenState.value = screenState.value.copy()

}

@Composable
fun CreateContent(
    viewState: State<CreateViewState>,
    originFork: MutableState<ForkUI?>,
    onClick: () -> Unit,
) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(getStringResources().FORK)
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.name.orEmpty())) },
                "${getStringResources().NAME}*",

                ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(name = text)
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.description.orEmpty())) },
                getStringResources().DESCRIPTION,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(description = text)
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.htmlUrl.orEmpty())) },
                getStringResources().URL,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(htmlUrl = text)
            }
            HeaderText(getStringResources().OWNER)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentSize().clickable {
                    viewState.value.isChangeAvatarDialogVisible.value = true
                }) {
                AvatarImage(
                    avatarUrl = viewState.value.fork.value?.owner?.avatarUrl.orEmpty(),
                    avatarSize = LocalLargeAvatarSize.current.size
                )
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.login.orEmpty())) },
                "${getStringResources().NAME}*",
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(
                    owner = viewState.value.fork.value?.owner?.copy(login = text)
                )
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.url.orEmpty())) },
                getStringResources().URL,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(
                    owner = viewState.value.fork.value?.owner?.copy(url = text)
                )
            }
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size),
                shape = MaterialTheme.shapes.large,
                enabled = originFork.value != viewState.value.fork.value && viewState.value.fork.value?.isForkValid() == true
            ) {
                Text(
                    text = getStringResources().SUBMIT, modifier = Modifier
                        .padding(vertical = LocalSmallPadding.current.size)
                )
            }
        }
    }
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