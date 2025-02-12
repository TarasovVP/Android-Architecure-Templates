package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.execute
import com.vnteam.architecturetemplates.fake.data.repositoryimpl.FakeDBRepository
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

class ClearDemoObjectsUseCaseTest : KoinTest {

    private val clearDemoObjectUseCase by inject<ClearDemoObjectUseCase>()
    private val repository by injectAs<DBRepository, FakeDBRepository>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<DBRepository> { FakeDBRepository() }
                }
            )
        }
    }

    @Test
    fun testClearDemoObjects() = runTest {
        clearDemoObjectUseCase.execute()
        assertTrue(repository.isClearDemoObjectsCalled)
    }
}