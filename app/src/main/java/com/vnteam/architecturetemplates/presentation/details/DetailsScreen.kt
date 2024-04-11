package com.vnteam.architecturetemplates.presentation.details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vnteam.architecturetemplates.R
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(forkId: Long?, onClick: () -> Unit) {
    val viewModel: DetailsViewModel = koinViewModel()
    val fork = viewModel.forkFlow.collectAsState()
    val isLoading = viewModel.progressVisibilityFlow.collectAsState()
    val error = viewModel.errorFlow.collectAsState()
    LaunchedEffect(forkId) {
        viewModel.getForkById(forkId)
    }
    val context = LocalContext.current
    LaunchedEffect(error.value) {
        error.value?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
    DetailsContent(fork.value, isLoading.value, onClick)
}

@Composable
fun DetailsContent(fork: ForkUI?, isLoading: Boolean?, onClick: () -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = fork?.name.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(
                text = fork?.owner?.login.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Card {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(fork?.owner?.avatarUrl.orEmpty())
                            .crossfade(true)
                            .error(R.drawable.ic_person)
                            .placeholder(R.drawable.ic_person)
                            .build(),
                        contentDescription = "Owner avatar",
                        modifier = Modifier
                            .wrapContentSize()
                            .width(50.dp)
                            .height(50.dp),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = fork?.description.orEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
            Text(
                text = "Description:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(
                text = fork?.description.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Back")
            }
        }
        if (isLoading == true) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}