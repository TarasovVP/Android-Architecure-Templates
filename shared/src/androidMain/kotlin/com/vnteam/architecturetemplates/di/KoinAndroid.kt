package com.vnteam.architecturetemplates.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun initKoin(application: Application) =
    startKoin {
        androidLogger()
        androidContext(application)
        modules(appModule + androidModule)
    }
