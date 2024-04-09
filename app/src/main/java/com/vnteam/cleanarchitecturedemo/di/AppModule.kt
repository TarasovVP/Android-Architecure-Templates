package com.vnteam.cleanarchitecturedemo.di

import com.google.gson.GsonBuilder
import com.vnteam.cleanarchitecturedemo.data.database.AppDatabase
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.ForkDBMapperImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.ForkResponseMapperImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.OwnerDBMapperImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.OwnerResponseMapperImpl
import com.vnteam.cleanarchitecturedemo.data.network.ApiService
import com.vnteam.cleanarchitecturedemo.data.repositoryimpl.ApiRepositoryImpl
import com.vnteam.cleanarchitecturedemo.data.repositoryimpl.DBRepositoryImpl
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerDBMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerUIMapper
import com.vnteam.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnteam.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnteam.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnteam.cleanarchitecturedemo.presentation.MainActivity.Companion.BASE_URL
import com.vnteam.cleanarchitecturedemo.presentation.MainActivity.Companion.SERVER_TIMEOUT
import com.vnteam.cleanarchitecturedemo.presentation.details.DetailsViewModel
import com.vnteam.cleanarchitecturedemo.presentation.list.ListViewModel
import com.vnteam.cleanarchitecturedemo.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.cleanarchitecturedemo.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.cleanarchitecturedemo.presentation.usecaseimpl.ForkUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {

    single { GsonBuilder().setLenient().create() }

    single {
        OkHttpClient.Builder()
            .connectTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(SERVER_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }

    single<ApiService> { get<Retrofit>().create(ApiService::class.java) }

    single {
        AppDatabase.getDatabase(get())
    }

    single { get<AppDatabase>().forkDao() }

    single<OwnerResponseMapper> { OwnerResponseMapperImpl() }

    single<OwnerDBMapper> { OwnerDBMapperImpl() }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<ForkResponseMapper> { ForkResponseMapperImpl(get()) }

    single<ForkDBMapper> { ForkDBMapperImpl(get()) }

    single<ForkUIMapper> { ForkUIMapperImpl(get()) }

    single<ApiRepository> { ApiRepositoryImpl(get(), get()) }

    single<DBRepository> { DBRepositoryImpl(get(), get()) }

    single<ForkUseCase> { ForkUseCaseImpl(get(), get()) }

    viewModel {
        ListViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}