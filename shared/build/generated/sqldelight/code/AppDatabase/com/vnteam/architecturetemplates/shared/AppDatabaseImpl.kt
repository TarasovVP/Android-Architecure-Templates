package com.vnteam.architecturetemplates.shared

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.vnteam.architecturetemplates.AppDatabase
import com.vnteam.architecturetemplates.AppDatabaseQueries
import com.vnteam.architecturetemplates.ForkWithOwner
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlDriver.Schema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueriesImpl = AppDatabaseQueriesImpl(this,
      driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS ForkWithOwner (
          |    id INTEGER PRIMARY KEY,
          |    name TEXT,
          |    fullName TEXT,
          |    htmlUrl TEXT,
          |    description TEXT,
          |    login TEXT,
          |    ownerId INTEGER,
          |    avatarUrl TEXT
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class AppDatabaseQueriesImpl(
  private val database: AppDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), AppDatabaseQueries {
  internal val getForkWithOwners: MutableList<Query<*>> = copyOnWriteList()

  internal val getForkWithOwnerById: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getForkWithOwners(mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
  ) -> T): Query<T> = Query(-1198519084, getForkWithOwners, driver, "AppDatabase.sq",
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

  public override fun getForkWithOwners(): Query<ForkWithOwner> = getForkWithOwners { id, name,
      fullName, htmlUrl, description, login, ownerId, avatarUrl ->
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

  public override fun <T : Any> getForkWithOwnerById(id: Long, mapper: (
    id: Long,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
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

  public override fun getForkWithOwnerById(id: Long): Query<ForkWithOwner> =
      getForkWithOwnerById(id) { id_, name, fullName, htmlUrl, description, login, ownerId,
      avatarUrl ->
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

  public override fun insertForkWithOwner(
    id: Long?,
    name: String?,
    fullName: String?,
    htmlUrl: String?,
    description: String?,
    login: String?,
    ownerId: Long?,
    avatarUrl: String?
  ): Unit {
    driver.execute(434123816, """
    |INSERT OR REPLACE INTO ForkWithOwner(id, name, fullName, htmlUrl, description, login, ownerId, avatarUrl)
    |VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    """.trimMargin(), 8) {
      bindLong(1, id)
      bindString(2, name)
      bindString(3, fullName)
      bindString(4, htmlUrl)
      bindString(5, description)
      bindString(6, login)
      bindLong(7, ownerId)
      bindString(8, avatarUrl)
    }
    notifyQueries(434123816, {database.appDatabaseQueries.getForkWithOwners +
        database.appDatabaseQueries.getForkWithOwnerById})
  }

  private inner class GetForkWithOwnerByIdQuery<out T : Any>(
    public val id: Long,
    mapper: (SqlCursor) -> T
  ) : Query<T>(getForkWithOwnerById, mapper) {
    public override fun execute(): SqlCursor = driver.executeQuery(-1020240911,
        """SELECT * FROM ForkWithOwner WHERE id = ?""", 1) {
      bindLong(1, id)
    }

    public override fun toString(): String = "AppDatabase.sq:getForkWithOwnerById"
  }
}
