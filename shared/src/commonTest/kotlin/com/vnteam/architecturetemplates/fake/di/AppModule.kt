package com.vnteam.architecturetemplates.fake.di

import com.vnteam.architecturetemplates.fake.data.local.FakePreferencesFactory
import com.vnteam.architecturetemplates.data.local.Preferences
import com.vnteam.architecturetemplates.data.repositoryimpl.PreferencesRepositoryImpl
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import com.vnteam.architecturetemplates.presentation.usecaseimpl.AppUseCaseImpl
import org.koin.dsl.module

val testModule = module(true) {

    single<Preferences> { FakePreferencesFactory() }

    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }

    single<AppUseCase> { AppUseCaseImpl(get()) }
}