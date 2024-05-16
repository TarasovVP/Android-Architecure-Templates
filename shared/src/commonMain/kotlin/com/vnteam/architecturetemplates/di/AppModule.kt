package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.data.network.BASE_URL
import com.vnteam.architecturetemplates.data.database.ForkDao
import com.vnteam.architecturetemplates.data.database.ForkDaoImpl
import com.vnteam.architecturetemplates.data.database.SharedDatabase
import com.vnteam.architecturetemplates.data.mapperimpls.ForkDBMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.ForkResponseMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.OwnerResponseMapperImpl
import com.vnteam.architecturetemplates.data.network.ApiService
import com.vnteam.architecturetemplates.data.repositoryimpl.ApiRepositoryImpl
import com.vnteam.architecturetemplates.data.repositoryimpl.DBRepositoryImpl
import com.vnteam.architecturetemplates.domain.mappers.ForkDBMapper
import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerResponseMapper
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import com.vnteam.architecturetemplates.presentation.viewmodels.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.ListViewModel
import com.vnteam.architecturetemplates.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.presentation.usecaseimpl.ForkUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import com.vnteam.architecturetemplates.presentation.mappers.OwnerUIMapper

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
                //TODO remove this line in production
                header("Access-Control-Allow-Origin", "http://localhost:8080")
                header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                header("Access-Control-Allow-Credentials", "true")
            }
        }
    }

    single {
        SharedDatabase(get())
    }

    single<ForkDao> {
        ForkDaoImpl(get<SharedDatabase>())
    }

    single<OwnerResponseMapper> { OwnerResponseMapperImpl() }

    single<ForkResponseMapper> { ForkResponseMapperImpl(get()) }

    single<ForkDBMapper> { ForkDBMapperImpl() }

    single<ApiRepository> { ApiRepositoryImpl(get(), get()) }

    single<DBRepository> { DBRepositoryImpl(get(), get()) }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<ForkUIMapper> { ForkUIMapperImpl(get()) }

    single<ForkUseCase> { ForkUseCaseImpl(get(), get()) }

    factory {
        ListViewModel(get(), get())
    }
    factory {
        DetailsViewModel(get(), get())
    }
}