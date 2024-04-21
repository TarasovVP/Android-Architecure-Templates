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
import com.vnteam.architecturetemplates.PlatformMessageDisplayer
import org.koin.compose.koinInject

@Composable
<<<<<<<< HEAD:app/src/iosMain/kotlin/com/vnteam/architecturetemplates/list/ListScreen.kt
fun ListScreen() {
    val navigator = LocalNavigator.currentOrThrow
========
fun ListScreen(onItemClick: (Long) -> Unit) {

>>>>>>>> 0d23c4c2 (Migrate to compose navigation):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/list/ListScreen.kt
    val viewModel: ListViewModel = koinInject()
    val platformMessageDisplayer: PlatformMessageDisplayer = koinInject()
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(viewState.value.error) {
        viewState.value.error?.let { message ->
            platformMessageDisplayer.showPopupMessage(message)
        }
    }
<<<<<<<< HEAD:app/src/iosMain/kotlin/com/vnteam/architecturetemplates/list/ListScreen.kt
    ListContent(viewState.value, { forkId ->
        navigator.push(NavigationScreen.DetailsContentScreen(forkId))
    }, {
========

    ListContent(viewState.value, onItemClick) {
>>>>>>>> 0d23c4c2 (Migrate to compose navigation):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/list/ListScreen.kt
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