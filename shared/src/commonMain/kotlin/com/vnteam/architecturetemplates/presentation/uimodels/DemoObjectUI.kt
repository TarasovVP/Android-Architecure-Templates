package com.vnteam.architecturetemplates.presentation.uimodels

import kotlinx.serialization.Serializable

@Serializable
<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/uimodels/DemoObjectUI.kt
data class DemoObjectUI(
========
data class ForkUI(
>>>>>>>> c0ecbca7 (Fix merge conflicts):shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/uimodels/ForkUI.kt
    var id: Long? = null,
    var name: String? = null,
    var fullName: String = "",
    var owner: OwnerUI? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
)