package com.vnteam.architecturetemplates.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForkDTO(
    var forkId: String? = null,
    var name: String? = null,
    var ownerDTO: OwnerDTO? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
)