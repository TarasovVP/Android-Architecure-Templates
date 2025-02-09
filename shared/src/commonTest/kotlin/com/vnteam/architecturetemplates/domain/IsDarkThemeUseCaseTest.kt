package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.IsDarkThemeUseCase
import com.vnteam.architecturetemplates.fake.data.repositoryimpl.FakePreferencesRepository
import com.vnteam.architecturetemplates.di.testModule
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
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IsDarkThemeUseCaseTest : KoinTest {

    private val isDarkTheme by inject<IsDarkThemeUseCase>()
    private val repository by inject<PreferencesRepository>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<PreferencesRepository> { FakePreferencesRepository() }
                }
            )
        }
    }

    @Test
    fun testSetIsDarkThemeTrue() = runTest {
        isDarkTheme.set(true)
        val actual = repository.getIsDarkTheme().first()
        assertTrue(actual)
    }

    @Test
    fun testSetIsDarkThemeFalse() = runTest {
        isDarkTheme.set(false)
        val actual = repository.getIsDarkTheme().first()
        assertFalse(actual)
    }

    @Test
    fun testGetIsDarkThemeTrue() = runTest {
        repository.setIsDarkTheme(true)
        val actual = isDarkTheme.get().first() == true
        assertTrue(actual)
    }

    @Test
    fun testGetIsDarkThemeFalse() = runTest {
        repository.setIsDarkTheme(false)
        val actual = isDarkTheme.get().first() == true
        assertFalse(actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}