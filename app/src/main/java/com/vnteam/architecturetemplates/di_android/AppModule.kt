package com.vnteam.architecturetemplates.di_android

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.presentation.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.usecase.ForkUseCase
import com.vnteam.architecturetemplates.presentation.details.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import com.vnteam.architecturetemplates.presentation.mapperimpls.ForkUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.usecaseimpl.ForkUseCaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val androidModule = module {

    single {
        DatabaseDriverFactory(androidContext())
    }

    single<OwnerUIMapper> { OwnerUIMapperImpl() }

    single<ForkUIMapper> { ForkUIMapperImpl(get()) }

    single<ForkUseCase> { ForkUseCaseImpl(get(), get()) }

    viewModel {
        ListViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}