package com.vnteam.architecturetemplates.data.database

import app.cash.sqldelight.db.SqlDriver
import com.vnteam.architecturetemplates.AppDatabase
import kotlinx.coroutines.await

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return initSqlDriver(AppDatabase.Schema).await()
    }
}