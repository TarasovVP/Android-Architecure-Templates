package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.LocalSmallAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalSmallPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import io.ktor.client.utils.EmptyContent
import presentation.ScreenState
import presentation.components.AvatarImage
import presentation.components.ConfirmationDialog
import presentation.components.EmptyState
import presentation.components.RefreshableLazyList

@Composable
fun ListScreen(screenState: MutableState<ScreenState>, onItemClick: (ForkUI) -> Unit) {
    val viewModel = viewModel(ListViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(viewState.value) {
        viewState.value.takeIf { it.forks == null }?.let {
            viewModel.processIntent(ListIntent.ClearForks())
            viewModel.processIntent(ListIntent.LoadForks(true))
        }
    }

    LaunchedEffect(viewState.value.infoMessage.value) {
        viewState.value.infoMessage.value.takeIf { it != null }?.let {
            screenState.value = screenState.value.copy(snackbarVisible = true, snackbarMessage = it.message, isSnackbarError = it.isError)
            viewState.value.infoMessage.value = null
        }
    }

    LaunchedEffect(viewState.value.isLoading) {
        screenState.value = screenState.value.copy(isProgressVisible = viewState.value.isLoading)
    }

    if (screenState.value.isScreenUpdatingNeeded) {
        viewModel.processIntent(ListIntent.LoadForks(true))
        screenState.value = screenState.value.copy(isScreenUpdatingNeeded = false)
    }

    ListContent(viewState.value) { forkUI, action ->
        when (action) {
            "refresh" -> viewModel.processIntent(ListIntent.LoadForks(false))
            "details" -> onItemClick(forkUI)
            "confirm_delete" -> {
                viewState.value.isConfirmationDialogVisible.value = true
                viewState.value.forkToDelete = forkUI.id.orEmpty()
            }
            "delete" -> {
                viewState.value.isConfirmationDialogVisible.value = false
                viewState.value.forkToDelete = ""
                viewModel.processIntent(ListIntent.DeleteFork( forkUI.id.orEmpty() ))
            }
        }
    }
}

@Composable
fun ListContent(viewState: ListViewState, onItemClick: (ForkUI, String) -> Unit) {
    Box {
        RefreshableLazyList(viewState.forks.isNullOrEmpty(), content = {
            items(viewState.forks.orEmpty()) { item ->
                ForkItem(item, onItemClick)
            }
        }, onRefresh = {
            onItemClick(ForkUI(), "refresh")
        })
        ConfirmationDialog(
            showDialog = viewState.isConfirmationDialogVisible,
            title = getStringResources().DELETE,
            onConfirmationClick = { onItemClick(ForkUI(id = viewState.forkToDelete), "delete") },
            onDismiss = { viewState.isConfirmationDialogVisible.value = false })
    }
}

@Composable
fun ForkItem(item: ForkUI, onItemClick: (ForkUI, String) -> Unit) {
    Card(modifier = Modifier.padding(LocalMediumPadding.current.size).fillMaxSize().clickable { onItemClick(item, "details") }) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSmallPadding.current.size)) {
            AvatarImage(avatarUrl = item.owner?.avatarUrl.orEmpty(), avatarSize = LocalMediumAvatarSize.current.size)
            Text(text = item.name.orEmpty(), modifier = Modifier
                .padding(LocalMediumPadding.current.size).weight(1f))
            IconButton(onClick = { onItemClick(item, "confirm_delete") }) {
                Icon( modifier = Modifier
                    .size(LocalSmallAvatarSize.current.size),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = getStringResources().DELETE,
                )
            }
        }
    }
}