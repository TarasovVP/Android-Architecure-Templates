package com.vnstudio.cleanarchitecturedemo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.BASE_URL
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.SERVER_TIMEOUT
import com.vnstudio.cleanarchitecturedemo.data.database.AppDatabase
import com.vnstudio.cleanarchitecturedemo.data.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.data.repositoryimpl.DBRepositoryImpl
import com.vnstudio.cleanarchitecturedemo.data.network.ApiService
import com.vnstudio.cleanarchitecturedemo.data.repositoryimpl.ApiRepositoryImpl
import com.vnstudio.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnstudio.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnstudio.cleanarchitecturedemo.presentation.usecaseimpl.ForkUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideForkDao(appDatabase: AppDatabase): ForkDao {
        return appDatabase.forkDao()
    }

    @Singleton
    @Provides
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideDBRepository(forkDao: ForkDao): DBRepository {
        return DBRepositoryImpl(forkDao)
    }

    @Singleton
    @Provides
    fun provideForkUseCase(apiRepository: ApiRepository, dbRepository: DBRepository): ForkUseCase {
        return ForkUseCaseImpl(apiRepository, dbRepository)
    }
}