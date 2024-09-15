package presentation.screens.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.vnteam.architecturetemplates.presentation.resources.DrawableResources
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalDefaultPadding
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import kotlinx.browser.window
import navigateUp
import presentation.components.AvatarImage
import presentation.components.ChangeAvatarDialog
import presentation.components.CommonTextField
import presentation.components.HeaderText
import presentation.components.PrimaryButton


@Composable
fun CreateContent(
    viewState: State<CreateViewState>,
    screenState: MutableState<ScreenState>,
    originFork: MutableState<ForkUI?>,
    onClick: () -> Unit,
) {
    CreateScreenStateContent(screenState, originFork.value?.name.isNullOrEmpty())
    val nameState =
        remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.name.orEmpty())) }
    val descriptionState =
        remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.description.orEmpty())) }
    val urlState =
        remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.htmlUrl.orEmpty())) }
    val ownerNameState =
        remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.login.orEmpty())) }
    val ownerUrlState =
        remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.url.orEmpty())) }

    LaunchedEffect(viewState.value.fork) {
        nameState.value = TextFieldValue(viewState.value.fork.value?.name.orEmpty())
        descriptionState.value = TextFieldValue(viewState.value.fork.value?.description.orEmpty())
        urlState.value = TextFieldValue(viewState.value.fork.value?.htmlUrl.orEmpty())
        ownerNameState.value = TextFieldValue(viewState.value.fork.value?.owner?.login.orEmpty())
        ownerUrlState.value = TextFieldValue(viewState.value.fork.value?.owner?.url.orEmpty())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(LocalDefaultPadding.current.size),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderText(LocalStringResources.current.FORK)
        CommonTextField(
            nameState,
            "${LocalStringResources.current.NAME}*",

            ) { text ->
            viewState.value.fork.value = viewState.value.fork.value?.copy(name = text)
        }
        CommonTextField(
            descriptionState,
            LocalStringResources.current.DESCRIPTION,
        ) { text ->
            viewState.value.fork.value =
                viewState.value.fork.value?.copy(description = text)
        }
        CommonTextField(
            urlState,
            LocalStringResources.current.URL,
        ) { text ->
            viewState.value.fork.value = viewState.value.fork.value?.copy(htmlUrl = text)
        }
        HeaderText(LocalStringResources.current.OWNER)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.wrapContentSize().padding(20.dp).clickable {
                viewState.value.isChangeAvatarDialogVisible.value = true
            }) {
            AvatarImage(
                resId = viewState.value.fork.value?.owner?.avatarUrl.orEmpty(),
                avatarSize = LocalLargeAvatarSize.current.size
            )
        }
        CommonTextField(
            ownerNameState,
            "${LocalStringResources.current.NAME}*",
        ) { text ->
            viewState.value.fork.value = viewState.value.fork.value?.copy(
                owner = viewState.value.fork.value?.owner?.copy(login = text)
            )
        }
        CommonTextField(
            ownerUrlState,
            LocalStringResources.current.URL,
        ) { text ->
            viewState.value.fork.value = viewState.value.fork.value?.copy(
                owner = viewState.value.fork.value?.owner?.copy(url = text)
            )
        }
        PrimaryButton(
            LocalStringResources.current.SUBMIT,
            originFork.value != viewState.value.fork.value && viewState.value.fork.value?.isForkValid() == true,
            Modifier,
            onClick = onClick
        )
    }
    ChangeAvatarDialog(viewState)
}

@Composable
fun CreateScreenStateContent(screenState: MutableState<ScreenState>, isCreate: Boolean) {
    screenState.value = screenState.value.copy(
        appBarState = screenState.value.appBarState.copy(
            appBarTitle = if (isCreate) LocalStringResources.current.CREATE else LocalStringResources.current.EDIT,
            topAppBarAction = {
                window.navigateUp()
            }
        ),
        floatingActionState = screenState.value.floatingActionState.copy(
            floatingActionButtonVisible = false
        ))
}

@Composable
fun ChangeAvatarDialog(viewState: State<CreateViewState>) {
    val isChangeAvatarDialogVisible =
        remember { mutableStateOf(viewState.value.isChangeAvatarDialogVisible.value) }
    LaunchedEffect(isChangeAvatarDialogVisible) {
        isChangeAvatarDialogVisible.value = viewState.value.isChangeAvatarDialogVisible.value
    }
    if (isChangeAvatarDialogVisible.value) {
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