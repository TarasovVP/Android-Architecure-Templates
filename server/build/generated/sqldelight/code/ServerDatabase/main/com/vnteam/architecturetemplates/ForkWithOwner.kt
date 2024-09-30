package com.vnteam.architecturetemplates

import kotlin.Int
import kotlin.String

public data class ForkWithOwner(
  public val id: Int,
  public val forkId: String,
  public val name: String?,
  public val htmlUrl: String?,
  public val description: String?,
  public val login: String?,
  public val ownerId: String?,
  public val avatarUrl: String?,
  public val url: String?,
)
