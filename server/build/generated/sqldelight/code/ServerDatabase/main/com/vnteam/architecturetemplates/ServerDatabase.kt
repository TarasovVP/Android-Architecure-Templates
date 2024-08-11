package com.vnteam.architecturetemplates

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.vnteam.architecturetemplates.server.newInstance
import com.vnteam.architecturetemplates.server.schema
import kotlin.Unit

public interface ServerDatabase : Transacter {
  public val serverDatabaseQueries: ServerDatabaseQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = ServerDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): ServerDatabase =
        ServerDatabase::class.newInstance(driver)
  }
}
