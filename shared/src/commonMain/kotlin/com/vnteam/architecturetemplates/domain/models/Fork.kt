package com.vnteam.architecturetemplates.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Fork(
    var forkId: String? = null,
    var name: String? = null,
    var owner: Owner? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
)