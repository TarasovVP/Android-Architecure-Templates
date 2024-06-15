package com.vnteam.architecturetemplates

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver {
        val config = HikariConfig().apply {
            setJdbcUrl("jdbc:postgresql://localhost:5432/forks")
            driverClassName = "org.postgresql.Driver"
            username = "dbusername"
            password = "yourpassword"
        }
        val dataSource = HikariDataSource(config)
        return dataSource.asJdbcDriver()
    }
}