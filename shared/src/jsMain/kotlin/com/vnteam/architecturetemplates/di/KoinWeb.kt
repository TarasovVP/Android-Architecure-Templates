package com.vnteam.architecturetemplates.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(): KoinApplication =
    startKoin {
        modules(appModule, webModule)
    }
