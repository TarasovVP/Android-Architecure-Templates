package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.APP_LANG_EN
import com.vnteam.architecturetemplates.data.APP_LANG_UK
import com.vnteam.architecturetemplates.data.IS_DARK_THEME
import com.vnteam.architecturetemplates.data.local.FakePreferencesFactory
import com.vnteam.architecturetemplates.data.local.Preferences
import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
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

class PreferencesRepositoryTest : KoinTest {

    private val repository by inject<PreferencesRepository>()
    private val preferencesFactory by inject<Preferences>()

    @BeforeTest
    fun setup() {
        val overrideModule = module {
            single<Preferences> { FakePreferencesFactory() }
        }
        startKoin {
            modules(testModule + overrideModule)
        }
    }

    @Test
    fun testIsDarkThemeTrue() = runTest {
        repository.setIsDarkTheme(true)
        val actual = preferencesFactory.getBoolean(IS_DARK_THEME).first()
        assertTrue(actual)
    }

    @Test
    fun testIsDarkThemeFalse() = runTest {
        repository.setIsDarkTheme(false)
        val actual = preferencesFactory.getBoolean(IS_DARK_THEME).first()
        assertFalse(actual)
    }

    @Test
    fun testLanguageEn() = runTest {
        repository.setLanguage(APP_LANG_EN)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testLanguageUk() = runTest {
        repository.setLanguage(APP_LANG_UK)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}