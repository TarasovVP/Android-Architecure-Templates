package com.vnstudio.cleanarchitecturedemo.di

import com.vnstudio.cleanarchitecturedemo.list.ListFragment
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectListFragment(listFragment: ListFragment)
}