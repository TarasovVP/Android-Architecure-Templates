package com.vnstudio.cleanarchitecturedemo.di

import com.vnstudio.cleanarchitecturedemo.AppApplication
import com.vnstudio.cleanarchitecturedemo.JsonConverter
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
    fun provideJsonConverter(): JsonConverter {
        return JsonConverter()
    }

    @Provides
    fun provideListPresenter(valleyApiConnector: ValleyApiConnector, jsonConverter: JsonConverter): ListPresenter {
        return ListPresenter(valleyApiConnector, jsonConverter)
    }
}