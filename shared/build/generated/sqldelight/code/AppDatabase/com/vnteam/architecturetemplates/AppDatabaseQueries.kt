package com.vnteam.architecturetemplates

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public interface AppDatabaseQueries : Transacter {
  public fun <T : Any> getDemoObjectWithOwners(mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
  ) -> T): Query<T>

  public fun getDemoObjectWithOwners(): Query<DemoObjectWithOwner>

  public fun <T : Any> getDemoObjectWithOwnerById(id: Long, mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
  ) -> T): Query<T>

  public fun getDemoObjectWithOwnerById(id: Long): Query<DemoObjectWithOwner>

  public fun insertDemoObjectWithOwner(
    id: Long?,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
  ): Unit
}
