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
import org.koin.compose.koinInject
import presentation.ScreenState
import presentation.components.avatarImage
import presentation.components.ConfirmationDialog
import presentation.components.RefreshableLazyList

@Composable
fun ListScreen(screenState: MutableState<ScreenState>, onItemClick: (ForkUI) -> Unit) {
    val listViewModel = koinInject<ListViewModel>()
    val viewModel = androidx.lifecycle.viewmodel.compose.viewModel { listViewModel }
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewState.value.takeIf { it.forks == null }?.let {
            viewModel.processIntent(ListIntent.ClearForks())
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

    ListContent(viewState.value) { forkUI, action ->
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
            onConfirmationClick = { onItemClick(ForkUI(forkId = viewState.forkToDelete), "delete") },
            onDismiss = { viewState.isConfirmationDialogVisible.value = false })
    }
}

@Composable
fun ForkItem(item: ForkUI, onItemClick: (ForkUI, String) -> Unit) {
    Card(modifier = Modifier.padding(LocalMediumPadding.current.size).fillMaxSize().clickable { onItemClick(item, "details") }) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSmallPadding.current.size)) {
            avatarImage(resId = item.owner?.avatarUrl.orEmpty(), avatarSize = LocalMediumAvatarSize.current.size)
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