package com.vnteam.cleanarchitecturedemo.presentation.list

import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI

data class ListViewState(val forks: List<ForkUI>? = null,
                         val isLoading: Boolean = false,
                         val error: String? = null)
