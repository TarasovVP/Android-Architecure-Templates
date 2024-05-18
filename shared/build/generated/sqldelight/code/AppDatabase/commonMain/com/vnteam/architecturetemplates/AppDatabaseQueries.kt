package com.vnteam.architecturetemplates

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String

public class AppDatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getForkWithOwners(mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
  ) -> T): Query<T> = Query(-1_198_519_084, arrayOf("ForkWithOwner"), driver, "AppDatabase.sq",
      "getForkWithOwners",
      "SELECT ForkWithOwner.id, ForkWithOwner.name, ForkWithOwner.fullName, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl FROM ForkWithOwner") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getLong(6),
      cursor.getString(7)
    )
  }

  public fun getForkWithOwners(): Query<ForkWithOwner> = getForkWithOwners { id, name, fullName,
      htmlUrl, description, login, ownerId, avatarUrl ->
    ForkWithOwner(
      id,
      name,
      fullName,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl
    )
  }

  public fun <T : Any> getForkWithOwnerById(id: Long, mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
  ) -> T): Query<T> = GetForkWithOwnerByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getLong(6),
      cursor.getString(7)
    )
  }

  public fun getForkWithOwnerById(id: Long): Query<ForkWithOwner> = getForkWithOwnerById(id) { id_,
      name, fullName, htmlUrl, description, login, ownerId, avatarUrl ->
    ForkWithOwner(
      id_,
      name,
      fullName,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl
    )
  }

  public fun clearForks() {
    driver.execute(1_061_931_374, """DELETE FROM ForkWithOwner""", 0)
    notifyQueries(1_061_931_374) { emit ->
      emit("ForkWithOwner")
    }
  }

  public fun insertForkWithOwner(
    id: Long?,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
  ) {
    driver.execute(434_123_816, """
        |INSERT OR REPLACE INTO ForkWithOwner(id, name, fullName, htmlUrl, description, login, ownerId, avatarUrl)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 8) {
          bindLong(0, id)
          bindString(1, name)
          bindString(2, fullName)
          bindString(3, htmlUrl)
          bindString(4, description)
          bindString(5, login)
          bindLong(6, ownerId)
          bindString(7, avatarUrl)
        }
    notifyQueries(434_123_816) { emit ->
      emit("ForkWithOwner")
    }
  }

  private inner class GetForkWithOwnerByIdQuery<out T : Any>(
    public val id: Long,
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
        """SELECT ForkWithOwner.id, ForkWithOwner.name, ForkWithOwner.fullName, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl FROM ForkWithOwner WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "AppDatabase.sq:getForkWithOwnerById"
  }
}
