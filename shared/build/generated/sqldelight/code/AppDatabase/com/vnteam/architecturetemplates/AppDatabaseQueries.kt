package com.vnteam.architecturetemplates

import app.cash.sqldelight.Query
import app.cash.sqldelight.SuspendingTransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Long
import kotlin.String
import kotlin.Unit

public class AppDatabaseQueries(
  driver: SqlDriver,
) : SuspendingTransacterImpl(driver) {
  public fun <T : Any> getForkWithOwners(mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
  ) -> T): Query<T> = Query(-1198519084, arrayOf("ForkWithOwner"), driver, "AppDatabase.sq",
      "getForkWithOwners", "SELECT * FROM ForkWithOwner") { cursor ->
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

  public suspend fun insertForkWithOwner(
    id: Long?,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
  ): Unit {
    driver.execute(434123816, """
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
        }.await()
    notifyQueries(434123816) { emit ->
      emit("ForkWithOwner")
    }
  }

  private inner class GetForkWithOwnerByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    public override fun addListener(listener: Query.Listener): Unit {
      driver.addListener(listener, arrayOf("ForkWithOwner"))
    }

    public override fun removeListener(listener: Query.Listener): Unit {
      driver.removeListener(listener, arrayOf("ForkWithOwner"))
    }

    public override fun <R> execute(mapper: (SqlCursor) -> R): QueryResult<R> =
        driver.executeQuery(-1020240911, """SELECT * FROM ForkWithOwner WHERE id = ?""", mapper, 1)
        {
      bindLong(0, id)
    }

    public override fun toString(): String = "AppDatabase.sq:getForkWithOwnerById"
  }
}
