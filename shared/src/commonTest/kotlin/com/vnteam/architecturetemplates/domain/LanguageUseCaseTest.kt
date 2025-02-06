package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.data.APP_LANG_EN
import com.vnteam.architecturetemplates.data.APP_LANG_UK
import com.vnteam.architecturetemplates.domain.repositories.PreferencesRepository
import com.vnteam.architecturetemplates.domain.usecase.LanguageUseCase
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
import kotlin.test.assertEquals

class LanguageUseCaseTest : KoinTest {

    private val languageUseCase by inject<LanguageUseCase>()
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
    fun testSetLanguageEn() = runTest {
        languageUseCase.set(APP_LANG_EN)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testSetLanguageUk() = runTest {
        languageUseCase.set(APP_LANG_UK)
        val actual = repository.getLanguage().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @Test
    fun testGetLanguageEn() = runTest {
        repository.setLanguage(APP_LANG_EN)
        val actual = languageUseCase.get().first()
        assertEquals(APP_LANG_EN, actual)
    }

    @Test
    fun testGetLanguageUk() = runTest {
        repository.setLanguage(APP_LANG_UK)
        val actual = languageUseCase.get().first()
        assertEquals(APP_LANG_UK, actual)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }
}