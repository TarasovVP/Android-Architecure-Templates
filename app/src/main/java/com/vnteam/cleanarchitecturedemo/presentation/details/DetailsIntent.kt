package com.vnteam.cleanarchitecturedemo.presentation.details

sealed class DetailsIntent {
    data class LoadFork(val forkId: Long) : DetailsIntent()
}