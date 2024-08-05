package com.vnteam.architecturetemplates.presentation.states

import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI


data class DetailsViewState(val fork: ForkUI? = null,
                            var successResult: Boolean = false
)
