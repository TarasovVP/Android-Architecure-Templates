package com.vnteam.architecturetemplates.db

import com.vnteam.architecturetemplates.ForkWithOwner
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.models.Owner

fun ForkWithOwner.toFork() = Fork(
  forkId = forkId,
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