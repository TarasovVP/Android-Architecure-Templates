package com.vnteam.architecturetemplates.data.database

import app.cash.sqldelight.async.coroutines.awaitCreate
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import com.vnteam.architecturetemplates.Constants
import com.vnteam.architecturetemplates.appdatabase.AppDatabase
import org.w3c.dom.Worker

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return WebWorkerDriver(
            Worker(
                js(Constants.SQL_JS_WORKER_URL),
            ),
        ).also { AppDatabase.Schema.awaitCreate(it) }
    }
}
