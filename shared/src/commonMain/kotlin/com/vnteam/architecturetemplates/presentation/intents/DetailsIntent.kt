package com.vnteam.architecturetemplates.presentation.intents

sealed class DetailsIntent {
    data class LoadFork(val forkId: Long) : DetailsIntent()
}