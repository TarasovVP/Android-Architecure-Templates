package com.vnteam.architecturetemplates

import app.cash.sqldelight.Query
import app.cash.sqldelight.SuspendingTransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class AppDatabaseQueries(
  driver: SqlDriver,
) : SuspendingTransacterImpl(driver) {
  public fun <T : Any> getForkWithOwners(mapper: (
    id: Long,
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = Query(-1_198_519_084, arrayOf("ForkWithOwner"), driver, "AppDatabase.sq",
      "getForkWithOwners",
      "SELECT ForkWithOwner.id, ForkWithOwner.forkId, ForkWithOwner.name, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl, ForkWithOwner.url FROM ForkWithOwner") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getForkWithOwners(): Query<ForkWithOwner> = getForkWithOwners { id, forkId, name,
      htmlUrl, description, login, ownerId, avatarUrl, url ->
    ForkWithOwner(
      id,
      forkId,
      name,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public fun <T : Any> getForkWithOwnerById(forkId: String, mapper: (
    id: Long,
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = GetForkWithOwnerByIdQuery(forkId) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getString(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getForkWithOwnerById(forkId: String): Query<ForkWithOwner> =
      getForkWithOwnerById(forkId) { id, forkId_, name, htmlUrl, description, login, ownerId,
      avatarUrl, url ->
    ForkWithOwner(
      id,
      forkId_,
      name,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public suspend fun clearForks() {
    driver.execute(1_061_931_374, """DELETE FROM ForkWithOwner""", 0).await()
    notifyQueries(1_061_931_374) { emit ->
      emit("ForkWithOwner")
    }
  }

  public suspend fun insertForkWithOwner(
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) {
    driver.execute(434_123_816, """
        |INSERT OR REPLACE INTO ForkWithOwner( forkId, name, htmlUrl, description, login, ownerId, avatarUrl, url)
        |VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 8) {
          bindString(0, forkId)
          bindString(1, name)
          bindString(2, htmlUrl)
          bindString(3, description)
          bindString(4, login)
          bindString(5, ownerId)
          bindString(6, avatarUrl)
          bindString(7, url)
        }.await()
    notifyQueries(434_123_816) { emit ->
      emit("ForkWithOwner")
    }
  }

  public suspend fun deleteForkWithOwnerById(forkId: String) {
    driver.execute(-1_712_853_144, """DELETE FROM ForkWithOwner WHERE forkId = ?""", 1) {
          bindString(0, forkId)
        }.await()
    notifyQueries(-1_712_853_144) { emit ->
      emit("ForkWithOwner")
    }
  }

  private inner class GetForkWithOwnerByIdQuery<out T : Any>(
    public val forkId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("ForkWithOwner", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("ForkWithOwner", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_020_240_911,
        """SELECT ForkWithOwner.id, ForkWithOwner.forkId, ForkWithOwner.name, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl, ForkWithOwner.url FROM ForkWithOwner WHERE forkId = ?""",
        mapper, 1) {
      bindString(0, forkId)
    }

    override fun toString(): String = "AppDatabase.sq:getForkWithOwnerById"
  }
}
