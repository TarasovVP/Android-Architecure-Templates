package com.vnteam.architecturetemplates.presentation.screens.create

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
import com.vnteam.architecturetemplates.components.AvatarImage
import com.vnteam.architecturetemplates.components.CommonTextField
import com.vnteam.architecturetemplates.components.HeaderText
import com.vnteam.architecturetemplates.components.PrimaryButton
import com.vnteam.architecturetemplates.navigateUp
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.resources.DrawableResources
import com.vnteam.architecturetemplates.resources.LocalDefaultPadding
import com.vnteam.architecturetemplates.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.resources.LocalMediumLargePadding
import com.vnteam.architecturetemplates.resources.LocalStringResources
import kotlinx.browser.window

@Composable
fun CreateContent(
    viewState: State<CreateViewState>,
    screenState: MutableState<ScreenState>,
    originDemoObject: MutableState<DemoObjectUI?>,
    onClick: () -> Unit,
) {
    CreateScreenStateContent(screenState, originDemoObject.value?.name.isNullOrEmpty())
    val nameState =
        remember { mutableStateOf(TextFieldValue(viewState.value.demoObject.value?.name.orEmpty())) }
    val descriptionState =
        remember { mutableStateOf(TextFieldValue(viewState.value.demoObject.value?.description.orEmpty())) }
    val urlState =
        remember { mutableStateOf(TextFieldValue(viewState.value.demoObject.value?.htmlUrl.orEmpty())) }
    val ownerNameState =
        remember { mutableStateOf(TextFieldValue(viewState.value.demoObject.value?.owner?.login.orEmpty())) }
    val ownerUrlState =
        remember { mutableStateOf(TextFieldValue(viewState.value.demoObject.value?.owner?.url.orEmpty())) }

    LaunchedEffect(viewState.value.demoObject) {
        nameState.value = TextFieldValue(viewState.value.demoObject.value?.name.orEmpty())
        descriptionState.value =
            TextFieldValue(viewState.value.demoObject.value?.description.orEmpty())
        urlState.value = TextFieldValue(viewState.value.demoObject.value?.htmlUrl.orEmpty())
        ownerNameState.value =
            TextFieldValue(viewState.value.demoObject.value?.owner?.login.orEmpty())
        ownerUrlState.value = TextFieldValue(viewState.value.demoObject.value?.owner?.url.orEmpty())
    }
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(LocalDefaultPadding.current.size),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderText(LocalStringResources.current.demoObject)
        CommonTextField(
            nameState,
            "${LocalStringResources.current.name}*",
        ) { text ->
            viewState.value.demoObject.value = viewState.value.demoObject.value?.copy(name = text)
        }
        CommonTextField(
            descriptionState,
            LocalStringResources.current.description,
        ) { text ->
            viewState.value.demoObject.value =
                viewState.value.demoObject.value?.copy(description = text)
        }
        CommonTextField(
            urlState,
            LocalStringResources.current.url,
        ) { text ->
            viewState.value.demoObject.value =
                viewState.value.demoObject.value?.copy(htmlUrl = text)
        }
        HeaderText(LocalStringResources.current.owner)
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier.wrapContentSize().padding(LocalMediumLargePadding.current.size).clickable {
                    viewState.value.isChangeAvatarDialogVisible.value = true
                },
        ) {
            AvatarImage(
                resId = viewState.value.demoObject.value?.owner?.avatarUrl.orEmpty(),
                avatarSize = LocalLargeAvatarSize.current.size,
            )
        }
        CommonTextField(
            ownerNameState,
            "${LocalStringResources.current.name}*",
        ) { text ->
            viewState.value.demoObject.value =
                viewState.value.demoObject.value?.copy(
                    owner = viewState.value.demoObject.value?.owner?.copy(login = text),
                )
        }
        CommonTextField(
            ownerUrlState,
            LocalStringResources.current.url,
        ) { text ->
            viewState.value.demoObject.value =
                viewState.value.demoObject.value?.copy(
                    owner = viewState.value.demoObject.value?.owner?.copy(url = text),
                )
        }
        PrimaryButton(
            LocalStringResources.current.submit,
            originDemoObject.value != viewState.value.demoObject.value &&
                viewState.value.demoObject.value?.isDemoObjectValid() == true,
            Modifier,
            onClick = onClick,
        )
    }
    if (viewState.value.isChangeAvatarDialogVisible.value) {
        com.vnteam.architecturetemplates.components.BaseChangeAvatarDialog(
            avatarList = DrawableResources.avatarList,
            onDismiss = {
                viewState.value.isChangeAvatarDialogVisible.value = false
            },
            onClick = { avatar ->
                viewState.value.demoObject.value =
                    viewState.value.demoObject.value?.copy(
                        owner = viewState.value.demoObject.value?.owner?.copy(avatarUrl = avatar),
                    )
                viewState.value.isChangeAvatarDialogVisible.value = false
            },
        )
    }
}

@Composable
fun CreateScreenStateContent(
    screenState: MutableState<ScreenState>,
    isCreate: Boolean,
) {
    screenState.value =
        screenState.value.copy(
            appBarState =
                screenState.value.appBarState.copy(
                    appBarTitle =
                        if (isCreate) {
                            LocalStringResources.current.create
                        } else {
                            LocalStringResources.current.edit
                        },
                    topAppBarAction = {
                        window.navigateUp()
                    },
                ),
            floatingActionState =
                screenState.value.floatingActionState.copy(
                    floatingActionButtonVisible = false,
                ),
        )
}

@Composable
fun ChangeAvatarDialog(viewState: State<CreateViewState>) {
    val isChangeAvatarDialogVisible =
        remember { mutableStateOf(viewState.value.isChangeAvatarDialogVisible.value) }
    LaunchedEffect(isChangeAvatarDialogVisible) {
        isChangeAvatarDialogVisible.value = viewState.value.isChangeAvatarDialogVisible.value
    }
    if (isChangeAvatarDialogVisible.value) {
        com.vnteam.architecturetemplates.components.BaseChangeAvatarDialog(
            avatarList = DrawableResources.avatarList,
            onDismiss = {
                viewState.value.isChangeAvatarDialogVisible.value = false
            },
            onClick = { avatar ->
                viewState.value.demoObject.value =
                    viewState.value.demoObject.value?.copy(
                        owner = viewState.value.demoObject.value?.owner?.copy(avatarUrl = avatar),
                    )
                viewState.value.isChangeAvatarDialogVisible.value = false
            },
        )
    }
}
