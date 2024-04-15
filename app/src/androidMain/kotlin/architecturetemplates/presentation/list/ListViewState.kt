package architecturetemplates.presentation.list

import com.vnteam.architecturetemplates.presentation.uimodels.ForkUI

data class ListViewState(val forks: List<ForkUI>? = null,
                         val isLoading: Boolean = false,
                         val error: String? = null)
