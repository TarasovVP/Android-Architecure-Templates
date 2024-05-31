package com.vnteam.architecturetemplates.presentation.intents

import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI

sealed class CreateIntent {
    data class LoadFork(val forkId: Long) : CreateIntent()
    data class CreateFork(val forkUI: ForkUI) : CreateIntent()
}