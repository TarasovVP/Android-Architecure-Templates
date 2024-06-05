package com.vnteam.architecturetemplates

import org.koin.dsl.module

val webModule = module {
    single<ForksPresenter> {
        ForksPresenterImpl(get())
    }
}