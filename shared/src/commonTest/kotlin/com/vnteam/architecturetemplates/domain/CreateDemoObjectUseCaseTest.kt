package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import com.vnteam.architecturetemplates.fake.data.repositoryimpl.FakeApiRepository
import com.vnteam.architecturetemplates.fake.domain.models.fakeDemoObject
import com.vnteam.architecturetemplates.injectAs
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class CreateDemoObjectUseCaseTest : KoinTest {

    private val createDemoObjectUseCase by inject<CreateDemoObjectUseCase>()
    private val repository by injectAs<ApiRepository, FakeApiRepository>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<ApiRepository> { FakeApiRepository() }
                }
            )
        }
    }

    @Test
    fun testCreateDemoObject() = runTest {
        createDemoObjectUseCase.execute(fakeDemoObject)
        assertTrue(repository.isInsertDemoObjectsToApiCalled)
    }
}