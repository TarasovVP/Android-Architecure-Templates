package com.vnteam.architecturetemplates.di_desktop

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val desktopModule = module {
    single {
        DatabaseDriverFactory()
    }
}