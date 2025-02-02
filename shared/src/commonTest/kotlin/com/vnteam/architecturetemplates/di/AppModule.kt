package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.data.repositoryimpl.PreferencesRepositoryImpl
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import org.koin.dsl.module

val testModule = module(true) {

    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
}