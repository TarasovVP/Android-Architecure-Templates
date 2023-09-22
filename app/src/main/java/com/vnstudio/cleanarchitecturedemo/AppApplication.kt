package com.vnstudio.cleanarchitecturedemo

import android.app.Application
import com.vnstudio.cleanarchitecturedemo.di.AppComponent
import com.vnstudio.cleanarchitecturedemo.di.AppModule
import com.vnstudio.cleanarchitecturedemo.di.DaggerAppComponent
import io.realm.Realm

class AppApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
    }

    companion object {
        var instance: AppApplication? = null
            private set
    }
}