package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.local.Preferences
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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

    private val testModule = module {
        single<Preferences> { FakePreferencesFactory() }
        single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    }

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule)
        }
    }

    @Test
    fun setIsDarkThemeTrue() = runTest {
        repository.setIsDarkTheme(true)
        val actual = preferencesFactory.getBoolean("is_dark_theme").first()
        assertTrue(actual)
    }

    @Test
    fun getIsDarkThemeReturnsFalseByDefault() = runTest {
        val actual = repository.getIsDarkTheme().first()
        assertFalse(actual)
    }

    @Test
    fun setLanguageEnAndGetBack() = runTest {
        repository.setLanguage("en")
        val actual = repository.getLanguage().first()
        assertEquals("en", actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    class FakePreferencesFactory : Preferences {
        private val booleanPrefs = mutableMapOf<String, Boolean>()
        private val stringPrefs = mutableMapOf<String, String?>()

        override suspend fun putBoolean(key: String, value: Boolean) {
            booleanPrefs[key] = value
        }

        override suspend fun getBoolean(key: String): Flow<Boolean> = flow {
            emit(booleanPrefs[key] ?: false)
        }

        override suspend fun putString(key: String, value: String) {
            stringPrefs[key] = value
        }

        override suspend fun getString(key: String): Flow<String?> = flow {
            emit(stringPrefs[key])
        }
    }
}