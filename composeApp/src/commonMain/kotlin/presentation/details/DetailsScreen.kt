package presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
import com.vnteam.architecturetemplates.presentation.resources.LocalLargeAvatarSize
import com.vnteam.architecturetemplates.presentation.resources.LocalLargePadding
import com.vnteam.architecturetemplates.presentation.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.presentation.resources.getStringResources
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import com.vnteam.architecturetemplates.presentation.viewmodels.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.viewModel
import presentation.components.AvatarImage
import presentation.components.CommonText

@Composable
fun DetailsScreen(forkId: Long?, onClick: () -> Unit) {
    val viewModel = viewModel(DetailsViewModel::class)
    val viewState = viewModel.state.collectAsState()

    LaunchedEffect(forkId) {
        viewModel.processIntent(DetailsIntent.LoadFork(forkId ?: 0))
    }

    DetailsContent(viewState.value, onClick)
}

@Composable
fun DetailsContent(viewState: DetailsViewState, onClick: () -> Unit) {
    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(LocalLargePadding.current.size),
            verticalArrangement = Arrangement.Top
        ) {
            CommonText(viewState.fork?.name.orEmpty())
            CommonText(viewState.fork?.owner?.login.orEmpty())
            OwnerCard(
                avatarUrl = viewState.fork?.owner?.avatarUrl.orEmpty(),
                description = viewState.fork?.description.orEmpty()
            )
            CommonText(getStringResources().DESCRIPTION)
            CommonText(viewState.fork?.description.orEmpty())
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LocalLargePadding.current.size)
            ) {
                Text(text = getStringResources().BACK)
            }
        }
        if (viewState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun OwnerCard(avatarUrl: String, description: String) {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalMediumPadding.current.size)
        ) {
            AvatarImage(avatarUrl, LocalLargeAvatarSize.current.size)
            CommonText(description)
        }
    }
}
