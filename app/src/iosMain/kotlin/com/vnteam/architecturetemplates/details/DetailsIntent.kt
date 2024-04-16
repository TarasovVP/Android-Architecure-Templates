package com.vnteam.architecturetemplates.details

sealed class DetailsIntent {
    data class LoadFork(val forkId: Long) : DetailsIntent()
}