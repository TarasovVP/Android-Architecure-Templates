package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.db.DatabaseDriverFactory
import com.vnteam.architecturetemplates.fork_service.ForkService
import com.vnteam.architecturetemplates.fork_service.ForkServiceImpl
import com.vnteam.architecturetemplates.ServerDatabase
import com.vnteam.architecturetemplates.ServerDatabase.Companion.Schema
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serverModule = module {
    single {
        DatabaseDriverFactory()
    }
    single { get<DatabaseDriverFactory>().createDriver() }
    single {
        ServerDatabase(get()).apply {
            Schema.create(get())
        }
    }
    single {
        get<ServerDatabase>().serverDatabaseQueries
    }
    single { Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    } }
    single<ForkService> {
        ForkServiceImpl(get())
    }
}