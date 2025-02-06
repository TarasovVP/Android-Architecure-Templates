package com.vnteam.architecturetemplates.presentation.viewmodels

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.domain.usecase.CreateDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase
import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeCreateDemoObjectUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeGetDemoObjectUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeInsertDemoObjectsUseCase
import com.vnteam.architecturetemplates.presentation.intents.CreateIntent
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
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
class CreateViewModelTest : KoinTest {

    private val createViewModel by inject<CreateViewModel>()

    private val fakeGetDemoObjectUseCase by inject<GetDemoObjectUseCase>()
    private val fakeInsertDemoObjectsUseCase by inject<InsertDemoObjectsUseCase>()
    private val fakeCreateDemoObjectUseCase by inject<CreateDemoObjectUseCase>()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<GetDemoObjectUseCase> { FakeGetDemoObjectUseCase() }
                    single<InsertDemoObjectsUseCase> { FakeInsertDemoObjectsUseCase() }
                    single<CreateDemoObjectUseCase> { FakeCreateDemoObjectUseCase() }
                }
            )
        }
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun testLoadDemoObject() = runTest {
        val demoObject = DemoObject("123", "ObjectName")
        (fakeGetDemoObjectUseCase as? FakeGetDemoObjectUseCase)?.demoObject = demoObject

        createViewModel.processIntent(CreateIntent.LoadDemoObject(demoObject.demoObjectId.orEmpty()))
        runCurrent()

        val currentState = createViewModel.state.first()
        val uiObject = currentState.demoObject.value
        val expectedDemoObject = DemoObjectUI(demoObject.demoObjectId, demoObject.name, OwnerUI())
        assertEquals(expectedDemoObject, uiObject)
        assertFalse(currentState.successResult)
    }

    @Test
    fun testCreateDemoObject() = runTest {
        val demoObjectUI = DemoObjectUI("123", "ObjectName", OwnerUI())

        createViewModel.processIntent(CreateIntent.CreateDemoObject(demoObjectUI))
        runCurrent()

        val expectedDemoObject = DemoObject(demoObjectUI.demoObjectId, demoObjectUI.name, Owner())
        assertEquals((fakeCreateDemoObjectUseCase as? FakeCreateDemoObjectUseCase)?.demoObject, expectedDemoObject)
        assertEquals((fakeInsertDemoObjectsUseCase as? FakeInsertDemoObjectsUseCase)?.demoObjects, listOf(expectedDemoObject))

        val currentState = createViewModel.state.first()
        assertTrue(currentState.successResult)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}