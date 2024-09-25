package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.data.network.BASE_URL
import com.vnteam.architecturetemplates.AppDatabase
import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import com.vnteam.architecturetemplates.data.database.DemoObject
import com.vnteam.architecturetemplates.data.database.DemoObjectImpl
import com.vnteam.architecturetemplates.data.mapperimpls.DemoObjectDBMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.DemoObjectResponseMapperImpl
import com.vnteam.architecturetemplates.data.mapperimpls.OwnerResponseMapperImpl
import com.vnteam.architecturetemplates.data.network.ApiService
import com.vnteam.architecturetemplates.data.repositoryimpl.ApiRepositoryImpl
import com.vnteam.architecturetemplates.data.repositoryimpl.DBRepositoryImpl
import com.vnteam.architecturetemplates.domain.mappers.DemoObjectDBMapper
import com.vnteam.architecturetemplates.domain.mappers.DemoObjectResponseMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerResponseMapper
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
        }
    }

    single {
        val sqlDriver = get<DatabaseDriverFactory>().createDriver()
        AppDatabase(sqlDriver)
    }

    single<DemoObject> { DemoObjectImpl(get<AppDatabase>().appDatabaseQueries) }

    single<OwnerResponseMapper> { OwnerResponseMapperImpl() }

    single<DemoObjectResponseMapper> { DemoObjectResponseMapperImpl(get()) }

    single<DemoObjectDBMapper> { DemoObjectDBMapperImpl() }

    single<ApiRepository> { ApiRepositoryImpl(get(), get()) }

    single<DBRepository> { DBRepositoryImpl(get(), get()) }
}