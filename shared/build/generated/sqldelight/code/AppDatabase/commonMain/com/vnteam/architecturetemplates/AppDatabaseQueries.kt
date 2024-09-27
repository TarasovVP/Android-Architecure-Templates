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
  public fun <T : Any> getDemoObjectWithOwners(mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = Query(1_798_923_796, arrayOf("DemoObjectWithOwner"), driver, "AppDatabase.sq",
      "getDemoObjectWithOwners",
      "SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.name, DemoObjectWithOwner.fullName, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner") {
      cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getLong(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getDemoObjectWithOwners(): Query<DemoObjectWithOwner> = getDemoObjectWithOwners { id,
      name, fullName, htmlUrl, description, login, ownerId, avatarUrl, url ->
    DemoObjectWithOwner(
      id,
      name,
      fullName,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public fun <T : Any> getDemoObjectWithOwnerById(id: Long, mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = GetDemoObjectWithOwnerByIdQuery(id) { cursor ->
    mapper(
      cursor.getLong(0)!!,
      cursor.getString(1),
      cursor.getString(2),
      cursor.getString(3),
      cursor.getString(4),
      cursor.getString(5),
      cursor.getLong(6),
      cursor.getString(7),
      cursor.getString(8)
    )
  }

  public fun getDemoObjectWithOwnerById(id: Long): Query<DemoObjectWithOwner> =
      getDemoObjectWithOwnerById(id) { id_, name, fullName, htmlUrl, description, login, ownerId,
      avatarUrl, url ->
    DemoObjectWithOwner(
      id_,
      name,
      fullName,
      htmlUrl,
      description,
      login,
      ownerId,
      avatarUrl,
      url
    )
  }

  public suspend fun clearDemoObjects() {
    driver.execute(484_767_086, """DELETE FROM DemoObjectWithOwner""", 0).await()
    notifyQueries(484_767_086) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  public suspend fun insertDemoObjectWithOwner(
    id: Long?,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?,
    url: String?,
  ) {
    driver.execute(-1_310_207_960, """
        |INSERT OR REPLACE INTO DemoObjectWithOwner(id, name, fullName, htmlUrl, description, login, ownerId, avatarUrl, url)
        |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 9) {
          bindLong(0, id)
          bindString(1, name)
          bindString(2, fullName)
          bindString(3, htmlUrl)
          bindString(4, description)
          bindString(5, login)
          bindLong(6, ownerId)
          bindString(7, avatarUrl)
          bindString(8, url)
        }.await()
    notifyQueries(-1_310_207_960) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  public suspend fun deleteDemoObjectWithOwnerById(id: Long) {
    driver.execute(1_224_497_384, """DELETE FROM DemoObjectWithOwner WHERE id = ?""", 1) {
          bindLong(0, id)
        }.await()
    notifyQueries(1_224_497_384) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  private inner class GetDemoObjectWithOwnerByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T,
  ) : Query<T>(mapper) {
    override fun addListener(listener: Query.Listener) {
      driver.addListener("DemoObjectWithOwner", listener = listener)
    }

    override fun removeListener(listener: Query.Listener) {
      driver.removeListener("DemoObjectWithOwner", listener = listener)
    }

    override fun <R> execute(mapper: (SqlCursor) -> QueryResult<R>): QueryResult<R> =
        driver.executeQuery(-864_453_967,
        """SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.name, DemoObjectWithOwner.fullName, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner WHERE id = ?""",
        mapper, 1) {
      bindLong(0, id)
    }

    override fun toString(): String = "AppDatabase.sq:getDemoObjectWithOwnerById"
  }
}
