package presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import com.vnteam.architecturetemplates.presentation.viewmodels.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import presentation.ScreenState
import presentation.components.AvatarImage
import presentation.components.HeaderText
import presentation.components.PrimaryText
import presentation.components.SecondaryText

@Composable
fun DetailsScreen(forkId: String?, screenState: MutableState<ScreenState>) {
    val viewModel = viewModel(DetailsViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(viewState.value.infoMessage.value) {
        viewState.value.infoMessage.value.takeIf { it != null }?.let {
            screenState.value = screenState.value.copy(snackbarVisible = true, snackbarMessage = it.message, isSnackbarError = it.isError)
        }
    }
    LaunchedEffect(viewState.value.isLoading) {
        screenState.value = screenState.value.copy(isProgressVisible = viewState.value.isLoading)
    }
    LaunchedEffect(forkId) {
        viewModel.processIntent(DetailsIntent.LoadFork(forkId.orEmpty()))
    }
    if (screenState.value.isScreenUpdatingNeeded) {
        viewModel.processIntent(DetailsIntent.LoadFork(forkId.orEmpty()))
        screenState.value = screenState.value.copy(isScreenUpdatingNeeded = false)
    }

    DetailsContent(viewState.value)
}

@Composable
fun DetailsContent(viewState: DetailsViewState) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            HeaderText(getStringResources().FORK)
            Row {
                SecondaryText(getStringResources().NAME)
                PrimaryText(viewState.fork?.name.orEmpty())
            }
            Row {
                SecondaryText(getStringResources().DESCRIPTION)
                PrimaryText(viewState.fork?.description.orEmpty())
            }
            Row {
                SecondaryText(getStringResources().URL)
                PrimaryText(viewState.fork?.htmlUrl.orEmpty())
            }
            HeaderText(getStringResources().OWNER)
            OwnerCard(viewState.fork?.owner)
        }
    }
}

@Composable
fun OwnerCard(ownerUI: OwnerUI?) {
    Card(modifier = Modifier.padding(top = LocalMediumPadding.current.size)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalMediumPadding.current.size)
        ) {
            AvatarImage(ownerUI?.avatarUrl.orEmpty(), LocalLargeAvatarSize.current.size)
            Column(modifier = Modifier.padding(start = LocalLargePadding.current.size, bottom = LocalMediumPadding.current.size))  {
                PrimaryText(ownerUI?.login.orEmpty())
                SecondaryText(ownerUI?.url.orEmpty())
            }
        }
    }
}
