package com.vnteam.architecturetemplates.presentation.viewmodels

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
open class BaseViewModelTest : KoinTest {

    @BeforeTest
    open fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    open fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}
