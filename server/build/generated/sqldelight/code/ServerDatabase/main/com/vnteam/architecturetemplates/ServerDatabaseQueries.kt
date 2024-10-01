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
  public fun <T : Any> getDemoObjectWithOwners(mapper: (
    id: Int,
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = Query(-1_230_830_734, arrayOf("DemoObjectWithOwner"), driver,
      "ServerDatabase.sq", "getDemoObjectWithOwners",
      "SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.demoObjectId, DemoObjectWithOwner.name, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner") {
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

  public fun getDemoObjectWithOwners(): Query<DemoObjectWithOwner> = getDemoObjectWithOwners { id,
      demoObjectId, name, htmlUrl, description, login, ownerId, avatarUrl, url ->
    DemoObjectWithOwner(
      id,
      demoObjectId,
      name,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public fun <T : Any> getDemoObjectWithOwnerById(demoObjectId: String, mapper: (
    id: Int,
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = GetDemoObjectWithOwnerByIdQuery(demoObjectId) { cursor ->
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

  public fun getDemoObjectWithOwnerById(demoObjectId: String): Query<DemoObjectWithOwner> =
      getDemoObjectWithOwnerById(demoObjectId) { id, demoObjectId_, name, htmlUrl, description,
      login, ownerId, avatarUrl, url ->
    DemoObjectWithOwner(
      id,
      demoObjectId_,
      name,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public fun deleteDemoObjectWithOwnerById(demoObjectId: String) {
    driver.execute(1_082_521_542, """DELETE FROM DemoObjectWithOwner WHERE demoObjectId = ?""", 1) {
          check(this is JdbcPreparedStatement)
          bindString(0, demoObjectId)
        }
    notifyQueries(1_082_521_542) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  public fun insertDemoObjectWithOwner(
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) {
    transaction {
      driver.execute(-273_403_849, """
          |INSERT INTO DemoObjectWithOwner(demoObjectId, name, htmlUrl, description, login, ownerId, avatarUrl, url)
          |    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
          |    ON CONFLICT (demoObjectId) DO UPDATE SET
          |        name = EXCLUDED.name,
          |        htmlUrl = EXCLUDED.htmlUrl,
          |        description = EXCLUDED.description,
          |        login = EXCLUDED.login,
          |        ownerId = EXCLUDED.ownerId,
          |        avatarUrl = EXCLUDED.avatarUrl,
          |        url = EXCLUDED.url
          """.trimMargin(), 8) {
            check(this is JdbcPreparedStatement)
            bindString(0, demoObjectId)
            bindString(1, name)
            bindString(2, htmlUrl)
            bindString(3, description)
            bindString(4, login)
            bindString(5, ownerId)
            bindString(6, avatarUrl)
            bindString(7, url)
          }
    }
    notifyQueries(-916_484_602) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  private inner class GetDemoObjectWithOwnerByIdQuery<out T : Any>(
    public val demoObjectId: String,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("DemoObjectWithOwner", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("DemoObjectWithOwner", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-1_543_931_757,
        """SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.demoObjectId, DemoObjectWithOwner.name, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner WHERE demoObjectId = ?""",
        mapper, 1) {
      check(this is JdbcPreparedStatement)
      bindString(0, demoObjectId)
    }

    override fun toString(): String = "ServerDatabase.sq:getDemoObjectWithOwnerById"
  }
}
