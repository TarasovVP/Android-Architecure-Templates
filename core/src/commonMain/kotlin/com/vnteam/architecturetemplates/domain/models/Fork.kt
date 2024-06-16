package com.vnteam.architecturetemplates.domain.models

data class Fork(
    var forkId: String? = null,
    var name: String? = null,
    var owner: Owner? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
)