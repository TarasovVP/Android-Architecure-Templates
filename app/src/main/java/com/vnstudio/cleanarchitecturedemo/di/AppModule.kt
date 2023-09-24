package com.vnstudio.cleanarchitecturedemo.di

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.vnstudio.cleanarchitecturedemo.database.AppDatabase
import com.vnstudio.cleanarchitecturedemo.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideRequestQueue(@ApplicationContext appContext: Context): RequestQueue {
        return Volley.newRequestQueue(appContext)
    }

    @Singleton
    @Provides
    fun provideValleyApiConnector(requestQueue: RequestQueue): ValleyApiConnector {
        return ValleyApiConnector(requestQueue)
    }

    @Singleton
    @Provides
    fun provideForkDao(appDatabase: AppDatabase): ForkDao {
        return appDatabase.forkDao()
    }

    @Singleton
    @Provides
    fun provideForkRepository(forkDao: ForkDao): ForkRepository {
        return ForkRepository(forkDao)
    }
}