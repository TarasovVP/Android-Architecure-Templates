package com.vnteam.architecturetemplates.di_android

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.details.DetailsViewModel
import presentation.list.ListViewModel

val androidModule = module {
    single {
        DatabaseDriverFactory(androidContext())
    }
    viewModel {
        ListViewModel(get(), get())
    }
    viewModel {
        DetailsViewModel(get(), get())
    }
}