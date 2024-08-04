package com.vnteam.architecturetemplates.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI

data class ListViewState(val forks: List<ForkUI>? = null,
                         var isConfirmationDialogVisible: MutableState<Boolean> = mutableStateOf(false),
                         var forkToDelete: String = "",
                         var successResult: Boolean = false
)