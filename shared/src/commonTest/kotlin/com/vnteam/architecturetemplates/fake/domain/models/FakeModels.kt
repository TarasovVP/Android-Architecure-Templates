package com.vnteam.architecturetemplates.fake.domain.models

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI

val fakeOwner = Owner(
    ownerId = "1",
    login = "Owner",
    avatarUrl = "https://example.com",
    url = "https://example.com"
)

val fakeOwner2 = Owner(
    ownerId = "2",
    login = "Owner2",
    avatarUrl = "https://example.com",
    url = "https://example.com"
)

val fakeDemoObject = DemoObject(
    demoObjectId = "1",
    name = "DemoObject",
    description = "DemoObject Description",
    owner = fakeOwner,
    htmlUrl = "https://example.com"
)

val fakeDemoObject2 = DemoObject(
    demoObjectId = "2",
    name = "DemoObject2",
    description = "DemoObject Description 2",
    owner = fakeOwner2,
    htmlUrl = "https://example.com"
)

val fakeDemoObjects = listOf(fakeDemoObject, fakeDemoObject2)

val fakeOwnerUI = OwnerUI(
    ownerId = fakeOwner.ownerId,
    login = fakeOwner.login,
    avatarUrl = fakeOwner.avatarUrl,
    url = fakeOwner.url
)

val fakeOwnerUI2 = OwnerUI(
    ownerId = fakeOwner2.ownerId,
    login = fakeOwner2.login,
    avatarUrl = fakeOwner2.avatarUrl,
    url = fakeOwner2.url
)

val fakeDemoObjectUI = DemoObjectUI(
    demoObjectId = fakeDemoObject.demoObjectId,
    name = fakeDemoObject.name,
    description = fakeDemoObject.description,
    owner = fakeOwnerUI,
    htmlUrl = fakeDemoObject.htmlUrl
)

val fakeDemoObjectUI2 = DemoObjectUI(
    demoObjectId = fakeDemoObject2.demoObjectId,
    name = fakeDemoObject2.name,
    description = fakeDemoObject2.description,
    owner = fakeOwnerUI2,
    htmlUrl = fakeDemoObject2.htmlUrl
)

val fakeDemoObjectsUI = listOf(fakeDemoObjectUI, fakeDemoObjectUI2)

val fakeException = Exception("Error")