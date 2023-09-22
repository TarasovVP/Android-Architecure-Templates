package com.vnstudio.cleanarchitecturedemo.di

import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.vnstudio.cleanarchitecturedemo.AppApplication
import com.vnstudio.cleanarchitecturedemo.database.RealmDBConnector
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import com.vnstudio.cleanarchitecturedemo.list.ListPresenter
import dagger.Module
import dagger.Provides
import io.realm.Realm

@Module
class AppModule(private val application: AppApplication) {

    @Provides
    fun provideRequestQueue(): RequestQueue {
        return Volley.newRequestQueue(application)
    }

    @Provides
    fun provideValleyApiConnector(requestQueue: RequestQueue): ValleyApiConnector {
        return ValleyApiConnector(requestQueue)
    }

    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    @Provides
    fun provideRealmDBConnector(realm: Realm): RealmDBConnector {
        return RealmDBConnector(realm)
    }

    @Provides
    fun provideListPresenter(valleyApiConnector: ValleyApiConnector, realmDBConnector: RealmDBConnector): ListPresenter {
        return ListPresenter(valleyApiConnector, realmDBConnector)
    }
}