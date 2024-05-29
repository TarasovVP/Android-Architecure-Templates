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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import presentation.components.painterRes
import com.vnteam.architecturetemplates.presentation.resources.DrawableResources
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.states.CreateViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import com.vnteam.architecturetemplates.presentation.viewmodels.CreateViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import presentation.ScaffoldState
import presentation.components.CommonText
import presentation.components.CommonTextField
import presentation.components.Snackbar

@Composable
fun CreateScreen() {
    val viewModel = viewModel(CreateViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        if (viewState.value.fork == null) {
            viewModel.state.value.fork = ForkUI(owner = OwnerUI())
        } else {
            //viewModel.processIntent(CreateIntent.LoadFork(viewState.value.fork!!.id))
        }
        //viewModel.processIntent(DetailsIntent.LoadFork(forkId ?: 0))
    }

    CreateContent(viewState.value) {
        println(viewState.value.fork)
        viewState.value.fork?.let { viewModel.processIntent(CreateIntent.CreateFork(it)) }
    }
}

@Composable
fun CreateContent(viewState: CreateViewState, onClick: () -> Unit) {
    val buttonEnabled = viewState.fork?.isForkValid() == true
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            CommonText("Fork:")
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.fork?.name.orEmpty())),
                "Name",
            ) { text ->
                viewState.fork?.name = text
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.fork?.description.orEmpty())),
                "Description",
            ) { text ->
                viewState.fork?.description = text
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.fork?.htmlUrl.orEmpty())),
                "Url",
            ) { text ->
                viewState.fork?.htmlUrl = text
            }
            CommonText("Owner")
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
                mutableStateOf(TextFieldValue(viewState.fork?.owner?.login.orEmpty())),
                "Name",
            ) { text ->
                viewState.fork?.owner?.login = text
            }
            CommonTextField(
                mutableStateOf(TextFieldValue(viewState.fork?.owner?.url.orEmpty())),
                "Url",
            ) { text ->
                viewState.fork?.owner?.url = text
            }
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size),
                enabled = viewState.fork?.isForkValid() == true
            ) {
                Text(text = "Save")
            }
        }
        viewState.infoMessage.value.takeIf { it != null }?.let {
            Snackbar(viewState.infoMessage)
        }
        if (viewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}