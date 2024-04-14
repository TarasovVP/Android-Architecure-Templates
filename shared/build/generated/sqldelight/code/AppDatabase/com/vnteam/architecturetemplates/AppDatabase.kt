package com.vnteam.architecturetemplates

import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import com.vnteam.architecturetemplates.shared.newInstance
import com.vnteam.architecturetemplates.shared.schema

public interface AppDatabase : Transacter {
  public val appDatabaseQueries: AppDatabaseQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = AppDatabase::class.schema

    public operator fun invoke(driver: SqlDriver): AppDatabase =
        AppDatabase::class.newInstance(driver)
  }
}
