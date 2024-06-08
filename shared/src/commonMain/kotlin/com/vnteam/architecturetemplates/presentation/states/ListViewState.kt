package com.vnteam.architecturetemplates.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI

data class ListViewState(val forks: List<ForkUI>? = null,
                         val isLoading: Boolean = false,
                         var isConfirmationDialogVisible: MutableState<Boolean> = mutableStateOf(false),
                         var forkToDelete: String = "",
                         var infoMessage: MutableState<InfoMessageState?> = mutableStateOf(null),
                         var successResult: Boolean = false
)
