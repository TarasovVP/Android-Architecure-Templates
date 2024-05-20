package com.vnteam.architecturetemplates.shared

import app.cash.sqldelight.SuspendingTransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.vnteam.architecturetemplates.AppDatabase
import com.vnteam.architecturetemplates.AppDatabaseQueries
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema<QueryResult.AsyncValue<Unit>>
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : SuspendingTransacterImpl(driver), AppDatabase {
  override val appDatabaseQueries: AppDatabaseQueries = AppDatabaseQueries(driver)

  public object Schema : SqlSchema<QueryResult.AsyncValue<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.AsyncValue<Unit> = QueryResult.AsyncValue {
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
          """.trimMargin(), 0).await()
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.AsyncValue<Unit> = QueryResult.AsyncValue {
    }
  }
}
