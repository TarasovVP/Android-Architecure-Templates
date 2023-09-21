package com.vnstudio.cleanarchitecturedemo.di

import com.vnstudio.cleanarchitecturedemo.list.ListPresenter
import com.vnstudio.cleanarchitecturedemo.models.DetailsPresenter
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideListPresenter(): ListPresenter {
        return ListPresenter()
    }

    @Provides
    fun provideDetailsPresenter(): DetailsPresenter {
        return DetailsPresenter()
    }
}