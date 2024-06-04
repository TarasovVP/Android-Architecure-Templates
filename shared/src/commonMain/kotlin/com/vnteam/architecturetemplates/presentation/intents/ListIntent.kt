package com.vnteam.architecturetemplates.presentation.intents

sealed class ListIntent {
     class ClearForks : ListIntent()
     class LoadForks(val isInit: Boolean) : ListIntent()
     class DeleteFork(val id: Long) : ListIntent()
}