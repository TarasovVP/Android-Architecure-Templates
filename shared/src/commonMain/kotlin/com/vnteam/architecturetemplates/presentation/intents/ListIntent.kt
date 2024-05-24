package com.vnteam.architecturetemplates.presentation.intents

sealed class ListIntent {
     class ClearForks : ListIntent()
     class LoadForks : ListIntent()
     class DeleteFork(val id: Long) : ListIntent()
}