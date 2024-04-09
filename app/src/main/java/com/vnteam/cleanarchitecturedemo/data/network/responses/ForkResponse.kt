package com.vnteam.cleanarchitecturedemo.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForkResponse(
    var id: Long? = null,
    var name: String? = null,
    @SerialName("full_name")
    var fullName: String? = null,
    var owner: OwnerResponse? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
)