package com.vnstudio.cleanarchitecturedemo.di

import com.vnstudio.cleanarchitecturedemo.AppApplication
import com.vnstudio.cleanarchitecturedemo.RealmDBConnector
import com.vnstudio.cleanarchitecturedemo.ValleyApiConnector
import com.vnstudio.cleanarchitecturedemo.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: AppApplication) {


    @Provides
    fun provideValleyApiConnector(): ValleyApiConnector {
        return ValleyApiConnector(application)
    }

    @Provides
    fun provideRealmDBConnector(): RealmDBConnector {
        return RealmDBConnector()
    }

    @Provides
    fun provideListPresenter(valleyApiConnector: ValleyApiConnector, realmDBConnector: RealmDBConnector): ListPresenter {
        return ListPresenter(valleyApiConnector, realmDBConnector)
    }
}