package com.vnteam.architecturetemplates.presentation.uimodels

import kotlinx.serialization.Serializable

@Serializable
data class ForkUI(
    var id: String? = null,
    var name: String? = null,
    var fullName: String = "",
    var owner: OwnerUI? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
) {
    fun isForkValid(): Boolean {
        return !name.isNullOrEmpty() && !owner?.login.isNullOrEmpty()
    }
}