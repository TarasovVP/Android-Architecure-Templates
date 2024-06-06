package com.vnteam.architecturetemplates.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    var login: String? = null,
    @SerialName("id")
    var ownerId: String? = null,
    @SerialName("avatar_url")
    var avatarUrl: String? = null,
    var url: String? = null
)