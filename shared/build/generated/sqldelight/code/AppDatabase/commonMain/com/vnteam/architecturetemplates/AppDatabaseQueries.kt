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
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = Query(1_798_923_796, arrayOf("DemoObjectWithOwner"), driver, "AppDatabase.sq",
      "getDemoObjectWithOwners",
      "SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.demoObjectId, DemoObjectWithOwner.name, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner") {
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
    id: Long,
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) -> T): Query<T> = GetDemoObjectWithOwnerByIdQuery(demoObjectId) { cursor ->
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

  public suspend fun clearDemoObjects() {
    driver.execute(484_767_086, """DELETE FROM DemoObjectWithOwner""", 0).await()
    notifyQueries(484_767_086) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  public suspend fun insertDemoObjectWithOwner(
    demoObjectId: String,
    name: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: String?,
    avatarUrl: String?,
    url: String?,
  ) {
    driver.execute(-1_310_207_960, """
        |INSERT OR REPLACE INTO DemoObjectWithOwner( demoObjectId, name, htmlUrl, description, login, ownerId, avatarUrl, url)
        |VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)
        """.trimMargin(), 8) {
          bindString(0, demoObjectId)
          bindString(1, name)
          bindString(2, htmlUrl)
          bindString(3, description)
          bindString(4, login)
          bindString(5, ownerId)
          bindString(6, avatarUrl)
          bindString(7, url)
        }.await()
    notifyQueries(-1_310_207_960) { emit ->
      emit("DemoObjectWithOwner")
    }
  }

  public suspend fun deleteDemoObjectWithOwnerById(demoObjectId: String) {
    driver.execute(1_224_497_384, """DELETE FROM DemoObjectWithOwner WHERE demoObjectId = ?""", 1) {
          bindString(0, demoObjectId)
        }.await()
    notifyQueries(1_224_497_384) { emit ->
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
        driver.executeQuery(-864_453_967,
        """SELECT DemoObjectWithOwner.id, DemoObjectWithOwner.demoObjectId, DemoObjectWithOwner.name, DemoObjectWithOwner.htmlUrl, DemoObjectWithOwner.description, DemoObjectWithOwner.login, DemoObjectWithOwner.ownerId, DemoObjectWithOwner.avatarUrl, DemoObjectWithOwner.url FROM DemoObjectWithOwner WHERE demoObjectId = ?""",
        mapper, 1) {
      bindString(0, demoObjectId)
    }

    override fun toString(): String = "AppDatabase.sq:getDemoObjectWithOwnerById"
  }
}
