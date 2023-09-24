package com.vnstudio.cleanarchitecturedemo.di

import android.content.Context
import com.android.volley.RequestQueue
import com.vnstudio.cleanarchitecturedemo.database.AppDatabase
import com.vnstudio.cleanarchitecturedemo.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.details.DetailsPresenter
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import com.vnstudio.cleanarchitecturedemo.list.ListPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
class AppModule() {

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideValleyApiConnector(requestQueue: RequestQueue): ValleyApiConnector {
        return ValleyApiConnector(requestQueue)
    }

    @Provides
    fun provideForkDao(appDatabase: AppDatabase): ForkDao {
        return appDatabase.forkDao()
    }

    @Provides
    fun provideForkRepository(forkDao: ForkDao): ForkRepository {
        return ForkRepository(forkDao)
    }

    @Provides
    fun provideListPresenter(valleyApiConnector: ValleyApiConnector, forkRepository: ForkRepository): ListPresenter {
        return ListPresenter(valleyApiConnector, forkRepository)
    }

    @Provides
    fun provideDetailsPresenter(forkRepository: ForkRepository): DetailsPresenter {
        return DetailsPresenter(forkRepository)
    }
}