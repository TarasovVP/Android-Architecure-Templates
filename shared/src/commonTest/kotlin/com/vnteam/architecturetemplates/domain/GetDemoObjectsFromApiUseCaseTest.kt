package com.vnteam.architecturetemplates.domain

import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.repositories.DBRepository
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromApiUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromDBUseCase
import com.vnteam.architecturetemplates.domain.usecase.execute
import com.vnteam.architecturetemplates.fake.data.repositoryimpl.FakeDBRepository
import com.vnteam.architecturetemplates.injectAs
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetDemoObjectsFromApiUseCaseTest : KoinTest {

    private val getDemoObjectsUseCase by inject<GetDemoObjectsFromApiUseCase>()
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
    fun testInsertDemoObjectsToDB() = runTest {
        val demoObjects = listOf(DemoObject("123", "ObjectName", null))
        repository.demoObjects = demoObjects
        val result = getDemoObjectsUseCase.execute().firstOrNull()
        assertEquals(demoObjects, result)
    }
}