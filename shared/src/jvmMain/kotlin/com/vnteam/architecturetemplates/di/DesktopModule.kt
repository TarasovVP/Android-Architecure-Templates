package com.vnteam.architecturetemplates.di

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import com.vnteam.architecturetemplates.presentation.details.DetailsViewModel
import com.vnteam.architecturetemplates.presentation.list.ListViewModel
import org.koin.dsl.module

val desktopModule = module {
    single {
        DatabaseDriverFactory()
    }
    factory {
        ListViewModel(get(), get(), get())
    }
    factory {
        DetailsViewModel(get(), get(), get())
    }
}