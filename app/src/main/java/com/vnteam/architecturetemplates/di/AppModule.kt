package com.vnstudio.cleanarchitecturedemo.di

import com.vnstudio.cleanarchitecturedemo.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideListPresenter(): ListPresenter {
        return ListPresenter()
    }
}