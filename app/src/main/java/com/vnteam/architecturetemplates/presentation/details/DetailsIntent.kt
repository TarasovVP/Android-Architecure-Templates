package com.vnteam.architecturetemplates.presentation.details

sealed class DetailsIntent {
    data class LoadFork(val forkId: Long) : DetailsIntent()
}