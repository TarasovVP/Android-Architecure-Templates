package benchmarks

import app.cash.sqldelight.db.SqlDriver
import com.vnteam.architecturetemplates.appdatabase.AppDatabase
import com.vnteam.architecturetemplates.data.database.SharedDatabase

internal actual fun createTestSqlDriver(): SqlDriver {
    val driver: SqlDriver = InMemorySqlDriver()
    return NativeSqliteDriver(AppDatabase.Schema, ":memory:")
}

internal actual fun createSharedDatabase(driver: SqlDriver): SharedDatabase {
    return SharedDatabase { AppDatabase(driver) }
}
