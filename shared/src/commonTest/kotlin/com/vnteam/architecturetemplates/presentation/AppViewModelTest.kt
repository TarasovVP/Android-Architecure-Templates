package com.vnteam.architecturetemplates.presentation

import com.vnteam.architecturetemplates.data.APP_LANG_EN
import com.vnteam.architecturetemplates.data.APP_LANG_UK
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import com.vnteam.architecturetemplates.fake.di.testModule
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeAppUseCase
import com.vnteam.architecturetemplates.presentation.viewmodels.AppViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AppViewModelTest : KoinTest {

    private val appViewModel by inject<AppViewModel>()
    private val appUseCase by inject<AppUseCase>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<AppUseCase> { FakeAppUseCase() }
                }
            )
        }
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun testSetIsDarkThemeTrue() = runTest {
        appViewModel.setIsDarkTheme(true)
        runCurrent()
        val actual = appUseCase.getIsDarkTheme().first()
        assertTrue(actual)
    }

    @Test
    fun testSetIsDarkThemeFalse() = runTest {
        appViewModel.setIsDarkTheme(false)
        runCurrent()
        val actual = appUseCase.getIsDarkTheme().first()
        assertFalse(actual)
    }

    @Test
    fun testGetIsDarkThemeTrue() = runTest {
        appUseCase.setIsDarkTheme(true)
        appViewModel.getIsDarkTheme()
        runCurrent()
        val actual = appViewModel.isDarkTheme.first() == true
        assertTrue(actual)
    }

    @Test
    fun testGetIsDarkThemeFalse() = runTest {
        appUseCase.setIsDarkTheme(false)
        appViewModel.getIsDarkTheme()
        runCurrent()
        val actual = appViewModel.isDarkTheme.first() == true
        assertFalse(actual)
    }

    @Test
    fun testSetLanguageEn() = runTest {
        appViewModel.setLanguage(APP_LANG_EN)
        runCurrent()
        val actual = appUseCase.getLanguage().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testSetLanguageUk() = runTest {
        appViewModel.setLanguage(APP_LANG_UK)
        runCurrent()
        val actual = appUseCase.getLanguage().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @Test
    fun testGetLanguageEn() = runTest {
        appUseCase.setLanguage(APP_LANG_EN)
        appViewModel.getLanguage()
        runCurrent()
        val actual = appViewModel.language.first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testGetLanguageUk() = runTest {
        appUseCase.setLanguage(APP_LANG_UK)
        appViewModel.getLanguage()
        runCurrent()
        val actual = appViewModel.language.first()
        assertEquals(APP_LANG_UK, actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}
