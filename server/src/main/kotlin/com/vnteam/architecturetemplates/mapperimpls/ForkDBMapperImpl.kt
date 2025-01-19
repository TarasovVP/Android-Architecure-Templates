package com.vnteam.architecturetemplates.mapperimpls

import com.vnteam.architecturetemplates.DemoObjectWithOwner
import com.vnteam.architecturetemplates.models.DemoObject
import com.vnteam.architecturetemplates.models.Owner

fun DemoObjectWithOwner.toDemoObject() = DemoObject(
    demoObjectId = demoObjectId,
    name = name,
    htmlUrl = htmlUrl,
    description = description,
    owner = Owner(
        login = login,
        ownerId = ownerId,
        avatarUrl = avatarUrl,
        url = url
    )
)