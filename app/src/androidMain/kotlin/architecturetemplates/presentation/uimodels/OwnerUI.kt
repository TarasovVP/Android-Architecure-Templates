package architecturetemplates.presentation.uimodels

import kotlinx.serialization.Serializable

@Serializable
data class OwnerUI(
    var login: String? = null,
    var ownerId: Long? = null,
    var avatarUrl: String? = null,
)