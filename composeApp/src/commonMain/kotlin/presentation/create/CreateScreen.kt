package presentation.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import presentation.components.painterRes
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
import presentation.components.CommonTextField
import presentation.components.HeaderText

@Composable
fun CreateScreen(forkId: Long, screenState: MutableState<ScreenState>) {
    val viewModel = viewModel(CreateViewModel::class)
    val viewState = viewModel.state.collectAsState()
    val originFork = mutableStateOf<ForkUI?>( null )

    LaunchedEffect(viewState.value) {
        originFork.value = viewState.value.fork.value
    }
    LaunchedEffect(Unit) {
        if (forkId > 0) {
            viewModel.processIntent(CreateIntent.LoadFork(forkId))
        } else {
            viewModel.state.value.fork = mutableStateOf( ForkUI(owner = OwnerUI()))
        }
    }
    LaunchedEffect(viewState.value.infoMessage.value) {
        viewState.value.infoMessage.value.takeIf { it != null }?.let {
            screenState.value = screenState.value.copy(snackbarVisible = true, snackbarMessage = it.message, isSnackbarError = it.isError)
        }
    }
    LaunchedEffect(viewState.value.isLoading) {
        screenState.value = screenState.value.copy(isProgressVisible = viewState.value.isLoading)
    }

    CreateContent(viewState, originFork) {
        viewState.value.fork.value?.let { viewModel.processIntent(CreateIntent.CreateFork(it)) }
    }
}

@Composable
fun CreateContent(viewState: State<CreateViewState>, originFork: MutableState<ForkUI?>, onClick: () -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            HeaderText(getStringResources().FORK)
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.value.fork.value?.name.orEmpty())),
                getStringResources().NAME,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(name = text)
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.value.fork.value?.description.orEmpty())),
                getStringResources().DESCRIPTION,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(description = text)
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.value.fork.value?.htmlUrl.orEmpty())),
                getStringResources().URL,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(htmlUrl = text)
            }
            HeaderText(getStringResources().OWNER)
            SubcomposeAsyncImage(
                model = "",
                contentDescription = getStringResources().OWNER_AVATAR,
                modifier = Modifier
                    .wrapContentSize()
                    .width(LocalLargeAvatarSize.current.size)
                    .height(LocalLargeAvatarSize.current.size)
                    .padding(horizontal = 5.dp),
                contentScale = ContentScale.Crop
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> CircularProgressIndicator()
                    is AsyncImagePainter.State.Error -> Image(painter = painterRes(DrawableResources.IC_PERSON), contentDescription = null)
                    else -> SubcomposeAsyncImageContent()
                }
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.login.orEmpty())),
                getStringResources().NAME,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(owner = viewState.value.fork.value?.owner?.copy(login = text))
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.value.fork.value?.owner?.url.orEmpty())),
                getStringResources().URL,
            ) { text ->
                viewState.value.fork.value = viewState.value.fork.value?.copy(owner = viewState.value.fork.value?.owner?.copy(url = text))
            }
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size),
                shape = MaterialTheme.shapes.large,
                enabled = viewState.value.fork.value?.isForkValid() == true && originFork.value != viewState.value.fork.value
            ) {
                Text(text = "Save", modifier = Modifier
                    .padding(vertical = LocalSmallPadding.current.size))
            }
        }
    }
}