package presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.LocalSmallAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalSmallPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import presentation.components.AvatarImage

@Composable
fun ListScreen(onItemClick: (Long) -> Unit, onButtonClick: () -> Unit) {
    val viewModel = viewModel(ListViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewState.value.takeIf { it.forks == null }?.let {
            viewModel.processIntent(ListIntent.ClearForks())
            viewModel.processIntent(ListIntent.LoadForks())
        }
    }

    ListContent(viewState.value, onButtonClick) { id, action ->
        when (action) {
            "details" -> onItemClick(id)
            "delete" -> viewModel.processIntent(ListIntent.DeleteFork())
        }
    }
}

@Composable
fun ListContent(viewState: ListViewState, onButtonClick: () -> Unit, onItemClick: (Long, String) -> Unit) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            ) {
                Text(text = getStringResources().ADD)
            }
            LazyColumn {
                items(viewState.forks.orEmpty()) { item ->
                    ForkItem(item, onItemClick)
                }
            }
        }
        if (viewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ForkItem(item: ForkUI, onItemClick: (Long, String) -> Unit) {
    Card(modifier = Modifier.padding(LocalMediumPadding.current.size).fillMaxSize().clickable { onItemClick(item.id ?: 0, "details") }) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSmallPadding.current.size)) {
            AvatarImage(avatarUrl = item.owner?.avatarUrl.orEmpty(), avatarSize = LocalMediumAvatarSize.current.size)
            Text(text = item.name.orEmpty(), modifier = Modifier
                .padding(LocalMediumPadding.current.size).weight(1f))
            IconButton(onClick = { onItemClick(item.id ?: 0, "delete") }) {
                Icon( modifier = Modifier
                    .size(LocalSmallAvatarSize.current.size),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = getStringResources().DELETE,
                )
            }
        }
    }
}