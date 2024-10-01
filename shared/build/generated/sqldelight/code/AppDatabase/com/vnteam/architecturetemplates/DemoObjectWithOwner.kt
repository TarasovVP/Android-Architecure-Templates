package com.vnteam.architecturetemplates

import kotlin.Long
import kotlin.String

public data class DemoObjectWithOwner(
  public val id: Long,
  public val name: String?,
  public val fullName: String?,
  public val htmlUrl: String?,
  public val description: String?,
  public val login: String?,
  public val ownerId: Long?,
  public val avatarUrl: String?
) {
  public override fun toString(): String = """
  |DemoObjectWithOwner [
  |  id: $id
  |  name: $name
  |  fullName: $fullName
  |  htmlUrl: $htmlUrl
  |  description: $description
  |  login: $login
  |  ownerId: $ownerId
  |  avatarUrl: $avatarUrl
  |]
  """.trimMargin()
}
