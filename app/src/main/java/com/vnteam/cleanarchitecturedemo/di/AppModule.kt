package com.vnteam.cleanarchitecturedemo.di

import android.util.Log
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.vnteam.cleanarchitecturedemo.AppDatabase
import com.vnteam.cleanarchitecturedemo.data.database.ForkDao
import com.vnteam.cleanarchitecturedemo.data.database.ForkDaoImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.ForkDBMapperImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.ForkResponseMapperImpl
import com.vnteam.cleanarchitecturedemo.data.mapperimpls.OwnerResponseMapperImpl
import com.vnteam.cleanarchitecturedemo.data.network.ApiService
import com.vnteam.cleanarchitecturedemo.data.repositoryimpl.ApiRepositoryImpl
import com.vnteam.cleanarchitecturedemo.data.repositoryimpl.DBRepositoryImpl
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerUIMapper
import com.vnteam.cleanarchitecturedemo.domain.repositories.ApiRepository
import com.vnteam.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnteam.cleanarchitecturedemo.domain.usecase.ForkUseCase
import com.vnteam.cleanarchitecturedemo.presentation.MainActivity.Companion.BASE_URL
import com.vnteam.cleanarchitecturedemo.presentation.details.DetailsViewModel
import com.vnteam.cleanarchitecturedemo.presentation.list.ListViewModel
import com.vnteam.cleanarchitecturedemo.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.cleanarchitecturedemo.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.cleanarchitecturedemo.presentation.usecaseimpl.ForkUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { BASE_URL }
    single { ApiService(get<String>(), get()) }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                header("Content-Type", "application/json")
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.e("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }

    single {
        val sqlDriver = AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = androidContext(),
            name = "forks.db"
        )
        AppDatabase(sqlDriver)
    }

    single<ForkDao> { ForkDaoImpl(get<AppDatabase>().appDatabaseQueries) }

    single<OwnerResponseMapper> { OwnerResponseMapperImpl() }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<ForkResponseMapper> { ForkResponseMapperImpl(get()) }

    single<ForkDBMapper> { ForkDBMapperImpl() }

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