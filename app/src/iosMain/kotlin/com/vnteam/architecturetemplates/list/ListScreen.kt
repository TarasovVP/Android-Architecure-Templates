package com.vnteam.architecturetemplates.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.vnteam.architecturetemplates.NavigationScreen
import com.vnteam.architecturetemplates.PlatformMessageDisplayer
import org.koin.compose.koinInject

@Composable
fun ListScreen() {
    val navigator = LocalNavigator.currentOrThrow
    val viewModel: ListViewModel = koinInject()
    val platformMessageDisplayer: PlatformMessageDisplayer = koinInject()
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(viewState.value.error) {
        viewState.value.error?.let { message ->
            platformMessageDisplayer.showPopupMessage(message)
        }
    }
    ListContent(viewState.value, { forkId ->
        navigator.push(NavigationScreen.DetailsContentScreen(forkId))
    }, {
        viewModel.processIntent(ListIntent.LoadForks())
    })
}

@Composable
fun ListContent(viewState: ListViewState, onItemClick: (Long) -> Unit, onButtonClick: () -> Unit) {

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = onButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Start")
            }
            LazyColumn {
                items(viewState.forks.orEmpty()) { item ->
                    Card(modifier = Modifier.padding(8.dp)) {
                        Text(text = item.name.orEmpty(), modifier = Modifier
                            .padding(8.dp)
                            .clickable { onItemClick(item.id ?: 0) })
                    }
                }
            }
        }
        if (viewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}