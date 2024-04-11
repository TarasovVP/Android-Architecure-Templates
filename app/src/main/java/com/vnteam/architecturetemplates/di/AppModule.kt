package com.vnteam.architecturetemplates.di

import android.util.Log
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.vnteam.architecturetemplates.AppDatabase
import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.data.database.ForkDaoImpl
import com.vnteam.architecturetemplates.data.mapperimpls.ForkDBMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.ForkResponseMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.OwnerResponseMapperImpl
import com.vnteam.architecturetemplates.data.network.ApiService
import com.vnteam.architecturetemplates.data.repositoryimpl.ApiRepositoryImpl
import com.vnteam.architecturetemplates.data.repositoryimpl.DBRepositoryImpl
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerResponseMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import com.vnteam.architecturetemplates.presentation.MainActivity.Companion.BASE_URL
import com.vnteam.architecturetemplates.presentation.details.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import com.vnteam.architecturetemplates.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.usecaseimpl.ForkUseCaseImpl
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