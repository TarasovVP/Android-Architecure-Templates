package com.vnstudio.cleanarchitecturedemo

import android.app.Application
import com.vnstudio.cleanarchitecturedemo.di.AppComponent
import com.vnstudio.cleanarchitecturedemo.di.AppModule
import com.vnstudio.cleanarchitecturedemo.di.DaggerAppComponent

class AppApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: AppApplication? = null
            private set
    }
}