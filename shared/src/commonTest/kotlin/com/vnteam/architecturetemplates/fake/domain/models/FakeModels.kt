package com.vnteam.architecturetemplates.fake.domain.models

import com.vnteam.architecturetemplates.DemoObjectWithOwner
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import kotlin.random.Random

val fakeOwner = Owner(
    ownerId = randomUuidLikeString(),
    login = "Owner",
    avatarUrl = "https://example.com",
    url = "https://example.com"
)

val fakeOwner2 = Owner(
    ownerId = randomUuidLikeString(),
    login = "Owner2",
    avatarUrl = "https://example.com",
    url = "https://example.com"
)

val fakeDemoObject = DemoObject(
    demoObjectId = randomUuidLikeString(),
    name = "DemoObject",
    description = "DemoObject Description",
    owner = fakeOwner,
    htmlUrl = "https://example.com"
)

val fakeDemoObject2 = DemoObject(
    demoObjectId = randomUuidLikeString(),
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

val fakeDemoObjectWithOwner = DemoObjectWithOwner(
    id = 0,
    demoObjectId = fakeDemoObject.demoObjectId.orEmpty(),
    name = fakeDemoObject.name,
    description = fakeDemoObject.description,
    htmlUrl = fakeDemoObject.htmlUrl,
    login = fakeOwner.login,
    ownerId = fakeOwner.ownerId,
    avatarUrl = fakeOwner.avatarUrl,
    url = fakeOwner.url.orEmpty()
)

val fakeDemoObjectWithOwner2 = DemoObjectWithOwner(
    id = 0,
    demoObjectId = fakeDemoObject2.demoObjectId.orEmpty(),
    name = fakeDemoObject2.name,
    description = fakeDemoObject2.description,
    htmlUrl = fakeDemoObject2.htmlUrl,
    login = fakeOwner2.login,
    ownerId = fakeOwner2.ownerId,
    avatarUrl = fakeOwner2.avatarUrl,
    url = fakeOwner2.url.orEmpty()
)

val fakeDemoObjectsWithOwner = listOf(fakeDemoObjectWithOwner, fakeDemoObjectWithOwner2)

val fakeException = Exception("Error")

fun randomUuidLikeString(): String {
    val bytes = ByteArray(16)
    Random.nextBytes(bytes)
    return bytes.joinToString("") {
        it.toString(16).padStart(2, '0')
    }
}
