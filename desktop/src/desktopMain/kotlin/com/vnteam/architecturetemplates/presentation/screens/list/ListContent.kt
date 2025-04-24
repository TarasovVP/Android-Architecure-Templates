package com.vnteam.architecturetemplates.presentation.screens.list

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vnteam.architecturetemplates.components.AvatarImage
import com.vnteam.architecturetemplates.components.ConfirmationDialog
import com.vnteam.architecturetemplates.components.RefreshableLazyList
import com.vnteam.architecturetemplates.domain.sealedclasses.ListState
import com.vnteam.architecturetemplates.presentation.states.ListViewState
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.resources.LocalMediumAvatarSize
import com.vnteam.architecturetemplates.resources.LocalMediumPadding
import com.vnteam.architecturetemplates.resources.LocalSmallAvatarSize
import com.vnteam.architecturetemplates.resources.LocalSmallPadding
import com.vnteam.architecturetemplates.resources.LocalStringResources

@Composable
fun ListContent(
    viewState: ListViewState,
    onItemClick: (DemoObjectUI, ListState) -> Unit,
) {
    Box {
        RefreshableLazyList(viewState.demoObjectUIs.isNullOrEmpty(), content = {
            items(viewState.demoObjectUIs.orEmpty()) { item ->
                DemoObjectItem(item, onItemClick)
            }
        }, onRefresh = {
            onItemClick(DemoObjectUI(), ListState.Refresh)
        })
        ConfirmationDialog(
            showDialog = viewState.isConfirmationDialogVisible,
            title = LocalStringResources.current.delete,
            onConfirmationClick = { onItemClick(DemoObjectUI(demoObjectId = viewState.demoObjectToDelete), ListState.Delete) },
            onDismiss = { viewState.isConfirmationDialogVisible.value = false },
        )
    }
}

@Composable
fun DemoObjectItem(
    item: DemoObjectUI,
    onItemClick: (DemoObjectUI, ListState) -> Unit,
) {
    Card(modifier = Modifier.padding(LocalMediumPadding.current.size).fillMaxSize().clickable { onItemClick(item, ListState.Details) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSmallPadding.current.size),
        ) {
            AvatarImage(resId = item.owner?.avatarUrl.orEmpty(), avatarSize = LocalMediumAvatarSize.current.size)
            Text(
                text = item.name.orEmpty(),
                modifier =
                    Modifier
                        .padding(LocalMediumPadding.current.size).weight(1f),
            )
            IconButton(onClick = { onItemClick(item, ListState.ConfirmDelete) }) {
                Icon(
                    modifier =
                        Modifier
                            .size(LocalSmallAvatarSize.current.size),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = LocalStringResources.current.delete,
                )
            }
        }
    }
}
