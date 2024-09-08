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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalDefaultPadding
import com.vnteam.architecturetemplates.presentation.resources.LocalStringResources
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import presentation.components.AvatarImage
import presentation.components.CommonTextField
import presentation.components.HeaderText
import presentation.components.PrimaryButton


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
                .padding(LocalDefaultPadding.current.size),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderText(LocalStringResources.current.FORK)
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.name.orEmpty())) },
                "${LocalStringResources.current.NAME}*",

                ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(name = text)
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.description.orEmpty())) },
                LocalStringResources.current.DESCRIPTION,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(description = text)
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.htmlUrl.orEmpty())) },
                LocalStringResources.current.URL,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(htmlUrl = text)
            }
            HeaderText(LocalStringResources.current.OWNER)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.wrapContentSize().clickable {
                    viewState.value.isChangeAvatarDialogVisible.value = true
                }) {
                AvatarImage(
                    resId = viewState.value.fork.value?.owner?.avatarUrl.orEmpty(),
                    avatarSize = LocalLargeAvatarSize.current.size
                )
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.login.orEmpty())) },
                "${LocalStringResources.current.NAME}*",
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(
                    owner = viewState.value.fork.value?.owner?.copy(login = text)
                )
            }
            CommonTextField(
                remember { mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.url.orEmpty())) },
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
    }
}