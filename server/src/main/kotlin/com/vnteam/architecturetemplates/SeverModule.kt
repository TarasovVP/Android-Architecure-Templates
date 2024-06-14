package com.vnteam.architecturetemplates

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.koin.dsl.module

val serverModule = module {
    single {
        HikariConfig().apply {
            setJdbcUrl("jdbc:postgresql://localhost:8081/forks")
            driverClassName = "org.postgresql.Driver"
            username = "dbusername"
            password = "dbpassword"
        }
    }
    single {
        HikariDataSource(get())
    }
    single {
        ServerDatabase(get<HikariDataSource>().asJdbcDriver())
    }
    single<ForkService> {
        ForkServiceImpl(get())
    }
}