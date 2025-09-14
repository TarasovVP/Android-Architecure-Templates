package com.vnteam.architecturetemplates.di

import org.koin.core.context.startKoin

fun initKoin() =
    startKoin {
        modules(appModule, iosModule)
    }
