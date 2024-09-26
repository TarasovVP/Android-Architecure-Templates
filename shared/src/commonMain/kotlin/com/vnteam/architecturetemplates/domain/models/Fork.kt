package com.vnteam.architecturetemplates.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/domain/models/DemoObject.kt
data class DemoObject(
========
data class Fork(
>>>>>>>> 6812e0ac (Transfer data and domain to shared):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/domain/models/Fork.kt
    var id: Long? = null,
    var name: String? = null,
    @SerialName("full_name")
    var fullName: String? = null,
    var owner: Owner? = null,
    @SerialName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
)