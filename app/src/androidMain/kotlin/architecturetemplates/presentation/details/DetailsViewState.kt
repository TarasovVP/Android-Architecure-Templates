package architecturetemplates.presentation.details

import architecturetemplates.presentation.uimodels.ForkUI


data class DetailsViewState(val fork: ForkUI? = null,
                            val isLoading: Boolean = false,
                            val error: String? = null)
