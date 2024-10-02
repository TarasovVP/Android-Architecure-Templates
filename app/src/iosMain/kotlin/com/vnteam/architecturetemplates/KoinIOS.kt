package com.vnteam.architecturetemplates
import com.vnteam.architecturetemplates.di.appModule
import org.koin.core.context.startKoin


fun doInitKoin() = startKoin {
    printLogger()
    modules(appModule, iosModule)
}