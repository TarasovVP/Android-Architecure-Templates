package com.vnteam.architecturetemplates

import android.app.Application
<<<<<<<< HEAD:app/src/androidMain/kotlin/com/vnteam/architecturetemplates/AppApplication.kt
import com.vnteam.architecturetemplates.di_android.androidModule
========
>>>>>>>> 0740c885 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/AppApplication.kt
import com.vnteam.architecturetemplates.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(appModule + androidModule)
        }
    }
}