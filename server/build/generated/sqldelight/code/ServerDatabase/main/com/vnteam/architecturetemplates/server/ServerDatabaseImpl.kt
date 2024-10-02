package com.vnteam.architecturetemplates.server

import app.cash.sqldelight.TransacterImpl
import app.cash.sqldelight.db.AfterVersion
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.vnteam.architecturetemplates.ServerDatabase
import com.vnteam.architecturetemplates.ServerDatabaseQueries
import kotlin.Long
import kotlin.Unit
import kotlin.reflect.KClass

internal val KClass<ServerDatabase>.schema: SqlSchema<QueryResult.Value<Unit>>
  get() = ServerDatabaseImpl.Schema

internal fun KClass<ServerDatabase>.newInstance(driver: SqlDriver): ServerDatabase =
    ServerDatabaseImpl(driver)

private class ServerDatabaseImpl(
  driver: SqlDriver,
) : TransacterImpl(driver), ServerDatabase {
  override val serverDatabaseQueries: ServerDatabaseQueries = ServerDatabaseQueries(driver)

  public object Schema : SqlSchema<QueryResult.Value<Unit>> {
    override val version: Long
      get() = 1

    override fun create(driver: SqlDriver): QueryResult.Value<Unit> {
      driver.execute(null, """
          |CREATE TABLE IF NOT EXISTS DemoObjectWithOwner (
          |    id SERIAL PRIMARY KEY,
          |    demoObjectId TEXT UNIQUE NOT NULL,
          |    name TEXT,
          |    htmlUrl TEXT,
          |    description TEXT,
          |    login TEXT,
          |    ownerId TEXT,
          |    avatarUrl TEXT,
          |    url TEXT
          |)
          """.trimMargin(), 0)
      return QueryResult.Unit
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Long,
      newVersion: Long,
      vararg callbacks: AfterVersion,
    ): QueryResult.Value<Unit> = QueryResult.Unit
  }
}
