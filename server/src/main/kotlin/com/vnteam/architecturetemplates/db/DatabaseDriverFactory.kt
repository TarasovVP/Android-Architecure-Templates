package com.vnteam.architecturetemplates.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.vnteam.architecturetemplates.DB_USER_NAME
import com.vnteam.architecturetemplates.DB_USER_PASSWORD
import com.vnteam.architecturetemplates.DRIVER_CLASS_NAME
import com.vnteam.architecturetemplates.JDBC_URL
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class DatabaseDriverFactory {
    fun createDriver(): SqlDriver {
        val config = HikariConfig().apply {
            jdbcUrl = JDBC_URL
            driverClassName = DRIVER_CLASS_NAME

            username = DB_USER_NAME
            password = DB_USER_PASSWORD

            maximumPoolSize = 5
            minimumIdle = 2
            idleTimeout = 10000
            connectionTimeout = 30000
            maxLifetime = 1800000
        }
        val dataSource = HikariDataSource(config)
        return dataSource.asJdbcDriver()
    }
}