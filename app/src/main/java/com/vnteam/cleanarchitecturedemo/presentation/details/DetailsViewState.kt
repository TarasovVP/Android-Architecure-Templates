package com.vnteam.cleanarchitecturedemo.presentation.details

import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI

data class DetailsViewState(val fork: ForkUI? = null,
                            val isLoading: Boolean = false,
                            val error: String? = null)
