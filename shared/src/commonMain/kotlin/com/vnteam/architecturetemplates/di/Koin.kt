@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.vnteam.architecturetemplates.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(config: (KoinApplication) -> Unit = {}) =
    startKoin {
        config.invoke(this)
        modules(appModule, platformModule())
    }

expect fun platformModule(): Module
