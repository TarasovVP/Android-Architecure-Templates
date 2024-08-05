package com.vnteam.architecturetemplates.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI


data class CreateViewState(var fork: MutableState<ForkUI?> = mutableStateOf(null),
                           var isChangeAvatarDialogVisible: MutableState<Boolean> = mutableStateOf(false),
                           var successResult: Boolean = false
)
