package com.vnteam.architecturetemplates.presentation.list

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
import androidx.compose.ui.unit.dp
import org.koin.compose.koinInject

@Composable
fun ListScreen(onItemClick: (Long) -> Unit) {
    val viewModel: ListViewModel = koinInject<ListViewModel>()
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