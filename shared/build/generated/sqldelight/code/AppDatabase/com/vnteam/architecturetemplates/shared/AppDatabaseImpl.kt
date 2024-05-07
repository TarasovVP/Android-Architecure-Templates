package com.vnteam.architecturetemplates.shared

import app.cash.sqldelight.SuspendingTransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.vnteam.architecturetemplates.AppDatabase
import com.vnteam.architecturetemplates.AppDatabaseQueries
import kotlin.Int
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<AppDatabase>.schema: SqlSchema
  get() = AppDatabaseImpl.Schema

internal fun KClass<AppDatabase>.newInstance(driver: SqlDriver): AppDatabase =
    AppDatabaseImpl(driver)

private class AppDatabaseImpl(
  driver: SqlDriver,
) : SuspendingTransacterImpl(driver), AppDatabase {
  public override val appDatabaseQueries: AppDatabaseQueries = AppDatabaseQueries(driver)

  public object Schema : SqlSchema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): QueryResult<Unit> = QueryResult.AsyncValue {
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

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int,
      vararg callbacks: AfterVersion,
    ): QueryResult<Unit> = QueryResult.AsyncValue {
    }
  }
}
