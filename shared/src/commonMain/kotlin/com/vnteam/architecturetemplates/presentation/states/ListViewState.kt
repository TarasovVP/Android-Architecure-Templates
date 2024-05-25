package com.vnteam.architecturetemplates.presentation.states

import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI

data class ListViewState(val forks: List<ForkUI>? = null,
                         val isLoading: Boolean = false,
                         var infoMessage: InfoMessageState? = null)
