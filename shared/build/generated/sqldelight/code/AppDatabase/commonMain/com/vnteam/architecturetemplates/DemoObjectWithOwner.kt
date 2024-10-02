package com.vnteam.architecturetemplates

import kotlin.Long
import kotlin.String

public data class DemoObjectWithOwner(
  public val id: Long,
  public val demoObjectId: String,
  public val name: String?,
  public val htmlUrl: String?,
  public val description: String?,
  public val login: String?,
  public val ownerId: String?,
  public val avatarUrl: String?,
  public val url: String?,
)
