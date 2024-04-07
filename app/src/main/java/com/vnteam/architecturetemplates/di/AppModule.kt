package com.vnstudio.cleanarchitecturedemo.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.BASE_URL
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.SERVER_TIMEOUT
import com.vnstudio.cleanarchitecturedemo.data.database.AppDatabase
import com.vnstudio.cleanarchitecturedemo.data.database.ForkDao
import com.vnstudio.cleanarchitecturedemo.data.mapperimpls.ForkDBMapperImpl
import com.vnstudio.cleanarchitecturedemo.data.mapperimpls.ForkResponseMapperImpl
import com.vnstudio.cleanarchitecturedemo.data.mapperimpls.OwnerDBMapperImpl
import com.vnstudio.cleanarchitecturedemo.data.mapperimpls.OwnerResponseMapperImpl
import com.vnstudio.cleanarchitecturedemo.data.repositoryimpl.DBRepositoryImpl
import com.vnstudio.cleanarchitecturedemo.data.network.ApiService
import com.vnstudio.cleanarchitecturedemo.data.repositoryimpl.ApiRepositoryImpl
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerDBMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnstudio.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnstudio.cleanarchitecturedemo.presentation.mapperimpls.ForkUIMapperImpl
import com.vnstudio.cleanarchitecturedemo.presentation.mapperimpls.OwnerUIMapperImpl
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
    fun provideOwnerResponseMapper(): OwnerResponseMapper {
        return OwnerResponseMapperImpl()
    }

    @Singleton
    @Provides
    fun provideOwnerDBMapper(): OwnerDBMapper {
        return OwnerDBMapperImpl()
    }

    @Singleton
    @Provides
    fun provideOwnerUIMapper(): OwnerUIMapper {
        return OwnerUIMapperImpl()
    }

    @Singleton
    @Provides
    fun provideForkResponseMapper(ownerResponseMapper: OwnerResponseMapper): ForkResponseMapper {
        return ForkResponseMapperImpl(ownerResponseMapper)
    }

    @Singleton
    @Provides
    fun provideForkDBMapper(ownerDBMapper: OwnerDBMapper): ForkDBMapper {
        return ForkDBMapperImpl(ownerDBMapper)
    }

    @Singleton
    @Provides
    fun provideForkUIMapper(ownerUIMapper: OwnerUIMapper): ForkUIMapper {
        return ForkUIMapperImpl(ownerUIMapper)
    }

    @Singleton
    @Provides
    fun provideApiRepository(apiService: ApiService, forkResponseMapper: ForkResponseMapper): ApiRepository {
        return ApiRepositoryImpl(apiService, forkResponseMapper)
    }

    @Singleton
    @Provides
    fun provideDBRepository(forkDao: ForkDao, forkDBMapper: ForkDBMapper): DBRepository {
        return DBRepositoryImpl(forkDao, forkDBMapper)
    }

    @Singleton
    @Provides
    fun provideForkUseCase(apiRepository: ApiRepository, dbRepository: DBRepository): ForkUseCase {
        return ForkUseCaseImpl(apiRepository, dbRepository)
    }
}