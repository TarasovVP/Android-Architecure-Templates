package com.vnteam.architecturetemplates

import app.cash.sqldelight.Transacter
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.vnteam.architecturetemplates.shared.newInstance
import com.vnteam.architecturetemplates.shared.schema
import kotlin.Unit

public interface AppDatabase : Transacter {
  public val appDatabaseQueries: AppDatabaseQueries

  public companion object {
    public val Schema: SqlSchema<QueryResult.Value<Unit>>
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}
