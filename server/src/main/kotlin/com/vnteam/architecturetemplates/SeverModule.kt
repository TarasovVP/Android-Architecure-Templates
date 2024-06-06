package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import org.koin.dsl.module

val serverModule = module {
    single {
        DatabaseDriverFactory()
    }
    single<ForksPresenter> {
        ForksPresenterImpl(get())
    }
}