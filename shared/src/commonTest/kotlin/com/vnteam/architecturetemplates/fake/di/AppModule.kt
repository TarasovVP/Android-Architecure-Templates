package com.vnteam.architecturetemplates.fake.di

import com.vnteam.architecturetemplates.data.local.Preferences
import com.vnteam.architecturetemplates.data.repositoryimpl.PreferencesRepositoryImpl
import com.vnteam.architecturetemplates.domain.mappers.DemoObjectUIMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.IsDarkThemeUseCase
import com.vnteam.architecturetemplates.domain.usecase.LanguageUseCase
import com.vnteam.architecturetemplates.fake.data.local.FakePreferencesFactory
import com.vnteam.architecturetemplates.presentation.mapperimpls.DemoObjectUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.usecaseimpl.IsDarkThemeUseCaseImpl
import com.vnteam.architecturetemplates.presentation.usecaseimpl.LanguageUseCaseImpl
import com.vnteam.architecturetemplates.presentation.viewmodels.AppViewModel
import com.vnteam.architecturetemplates.presentation.viewmodels.CreateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val testModule = module(true) {

    single<DemoObjectUIMapper> { DemoObjectUIMapperImpl(get()) }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<Preferences> { FakePreferencesFactory() }

    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }

    single<IsDarkThemeUseCase> { IsDarkThemeUseCaseImpl(get()) }

    single<LanguageUseCase> { LanguageUseCaseImpl(get()) }

    viewModel { AppViewModel(get(), get()) }

    viewModel { CreateViewModel(get(), get(), get(), get()) }
}