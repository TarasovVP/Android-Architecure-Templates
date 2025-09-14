package com.vnteam.architecturetemplates

import android.app.Application
import com.vnteam.architecturetemplates.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { koinApplication ->
            koinApplication.apply {
                androidLogger()
                androidContext(this@AppApplication)
            }
        }
    }
}
