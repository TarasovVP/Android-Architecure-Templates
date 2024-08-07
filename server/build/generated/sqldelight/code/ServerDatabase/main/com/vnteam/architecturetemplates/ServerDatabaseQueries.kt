package com.vnteam.architecturetemplates

import app.cash.sqldelight.Query
import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlCursor
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.JdbcCursor
import app.cash.sqldelight.driver.jdbc.JdbcPreparedStatement
import kotlin.Any
import kotlin.Int
import kotlin.String

public class ServerDatabaseQueries(
  driver: SqlDriver,
) : TransacterImpl(driver) {
  public fun <T : Any> getForkWithOwners(mapper: (
    id: Int,
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = Query(-983_922_510, arrayOf("ForkWithOwner"), driver, "ServerDatabase.sq",
      "getForkWithOwners",
      "SELECT ForkWithOwner.id, ForkWithOwner.forkId, ForkWithOwner.name, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl, ForkWithOwner.url FROM ForkWithOwner") {
      cursor ->
    check(cursor is JdbcCursor)
    mapper(
      cursor.getInt(0)!!,
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
    id: Int,
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = GetForkWithOwnerByIdQuery(forkId) { cursor ->
    check(cursor is JdbcCursor)
    mapper(
      cursor.getInt(0)!!,
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

  public fun deleteForkWithOwnerById(forkId: String) {
    driver.execute(-447_640_378, """DELETE FROM ForkWithOwner WHERE forkId = ?""", 1) {
          check(this is JdbcPreparedStatement)
          bindString(0, forkId)
        }
    notifyQueries(-447_640_378) { emit ->
      emit("ForkWithOwner")
    }
  }

  public fun insertForkWithOwner(
    forkId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) {
    transaction {
      driver.execute(-1_947_127_113, """
          |INSERT INTO ForkWithOwner(forkId, name, htmlUrl, description, login, ownerId, avatarUrl, url)
          |    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
          |    ON CONFLICT (forkId) DO UPDATE SET
          |        name = EXCLUDED.name,
          |        htmlUrl = EXCLUDED.htmlUrl,
          |        description = EXCLUDED.description,
          |        login = EXCLUDED.login,
          |        ownerId = EXCLUDED.ownerId,
          |        avatarUrl = EXCLUDED.avatarUrl,
          |        url = EXCLUDED.url
          """.trimMargin(), 8) {
            check(this is JdbcPreparedStatement)
            bindString(0, forkId)
            bindString(1, name)
            bindString(2, htmlUrl)
            bindString(3, description)
            bindString(4, login)
            bindString(5, ownerId)
            bindString(6, avatarUrl)
            bindString(7, url)
          }
    }
    notifyQueries(503_001_222) { emit ->
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
        driver.executeQuery(1_114_958_675,
        """SELECT ForkWithOwner.id, ForkWithOwner.forkId, ForkWithOwner.name, ForkWithOwner.htmlUrl, ForkWithOwner.description, ForkWithOwner.login, ForkWithOwner.ownerId, ForkWithOwner.avatarUrl, ForkWithOwner.url FROM ForkWithOwner WHERE forkId = ?""",
        mapper, 1) {
      check(this is JdbcPreparedStatement)
      bindString(0, forkId)
    }

    override fun toString(): String = "ServerDatabase.sq:getForkWithOwnerById"
  }
}
