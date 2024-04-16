package com.vnteam.architecturetemplates.presentation

import androidx.compose.runtime.Composable
import com.vnteam.architecturetemplates.presentation.details.DetailsScreen
import com.vnteam.architecturetemplates.presentation.list.ListScreen
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent

sealed class NavigationScreen: Screen, KoinComponent {
    class ListContentScreen : NavigationScreen() {
        @Composable
        override fun Content() {
            ListScreen()
        }
    }
    class DetailsContentScreen(val forkId: Long) : NavigationScreen() {
        @Composable
        override fun Content() {
            DetailsScreen(forkId = forkId)
        }
    }
}
