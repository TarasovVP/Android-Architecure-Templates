package com.vnteam.architecturetemplates

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serverModule = module {
    single {
        DatabaseDriverFactory()
    }
    single {
        ServerDatabase(get<DatabaseDriverFactory>().createDriver())
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