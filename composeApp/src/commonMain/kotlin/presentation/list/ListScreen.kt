package presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.list.ListIntent
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import com.vnteam.architecturetemplates.presentation.list.ListViewState
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.viewModel

@Composable
fun ListScreen(onItemClick: (Long) -> Unit) {
    val viewModel = viewModel(ListViewModel::class)
    val viewState = viewModel.state.collectAsState()

    ListContent(viewState.value, onItemClick) {
        viewModel.processIntent(ListIntent.LoadForks())
    }
}

@Composable
fun ListContent(viewState: ListViewState, onItemClick: (Long) -> Unit, onButtonClick: () -> Unit) {

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
                Text(text = getStringResources().START)
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
fun ForkItem(item: ForkUI, onItemClick: (Long) -> Unit) {
    Card(modifier = Modifier.padding(LocalMediumPadding.current.size).clickable { onItemClick(item.id ?: 0) }) {
        Text(text = item.name.orEmpty(), modifier = Modifier
            .padding(LocalMediumPadding.current.size))
    }
}