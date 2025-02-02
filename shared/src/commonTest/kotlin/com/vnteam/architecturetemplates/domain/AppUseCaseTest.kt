package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.data.APP_LANG_EN
import com.vnteam.architecturetemplates.data.APP_LANG_UK
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.AppUseCase
import com.vnteam.architecturetemplates.fake.data.repositoryimpl.FakePreferencesRepository
import com.vnteam.architecturetemplates.fake.di.testModule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
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

class AppUseCaseTest : KoinTest {

    private val appUseCase by inject<AppUseCase>()
    private val repository by inject<PreferencesRepository>()

    @BeforeTest
    fun setup() {
        val overrideModule = module {
            single<PreferencesRepository> { FakePreferencesRepository() }
        }
        startKoin {
            modules(testModule + overrideModule)
        }
    }

    @Test
    fun testSetIsDarkThemeTrue() = runTest {
        appUseCase.setIsDarkTheme(true)
        val actual = repository.getIsDarkTheme().first()
        assertTrue(actual)
    }

    @Test
    fun testSetIsDarkThemeFalse() = runTest {
        appUseCase.setIsDarkTheme(false)
        val actual = repository.getIsDarkTheme().first()
        assertFalse(actual)
    }

    @Test
    fun testGetIsDarkThemeTrue() = runTest {
        repository.setIsDarkTheme(true)
        val actual = appUseCase.getIsDarkTheme().first()
        assertTrue(actual)
    }

    @Test
    fun testGetIsDarkThemeFalse() = runTest {
        repository.setIsDarkTheme(false)
        val actual = appUseCase.getIsDarkTheme().first()
        assertFalse(actual)
    }

    @Test
    fun testSetLanguageEn() = runTest {
        appUseCase.setLanguage(APP_LANG_EN)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testSetLanguageUk() = runTest {
        appUseCase.setLanguage(APP_LANG_UK)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @Test
    fun testGetLanguageEn() = runTest {
        repository.setLanguage(APP_LANG_EN)
        val actual = appUseCase.getLanguage().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testGetLanguageUk() = runTest {
        repository.setLanguage(APP_LANG_UK)
        val actual = appUseCase.getLanguage().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}