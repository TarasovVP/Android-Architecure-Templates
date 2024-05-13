package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val webModule = module {
    single {
        DatabaseDriverFactory()
    }
}