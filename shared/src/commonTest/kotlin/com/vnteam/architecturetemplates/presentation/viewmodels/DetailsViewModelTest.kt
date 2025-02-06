package com.vnteam.architecturetemplates.presentation.viewmodels

import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectUseCase
import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeGetDemoObjectUseCase
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
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
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest : KoinTest {

    private val detailsViewModel by inject<DetailsViewModel>()

    private val fakeGetDemoObjectUseCase by inject<GetDemoObjectUseCase>()

    private val demoObject = DemoObject("123", "ObjectName", Owner())

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testModule + module {
                    single<GetDemoObjectUseCase> { FakeGetDemoObjectUseCase() }
                }
            )
        }
        Dispatchers.setMain(StandardTestDispatcher())
        (fakeGetDemoObjectUseCase as FakeGetDemoObjectUseCase).demoObject = demoObject
    }

    @Test
    fun testLoadDemoObject() = runTest {
        detailsViewModel.processIntent(DetailsIntent.LoadDemoObject(demoObject.demoObjectId.orEmpty(), isUpdated = false))
        runCurrent()

        val currentState = detailsViewModel.state.first()
        val expectedUI = DemoObjectUI(demoObject.demoObjectId, demoObject.name, OwnerUI())
        assertEquals(expectedUI, currentState.demoObjectUI)
    }

    @Test
    fun testLoadDemoObjectIsUpdatedClearsState() = runTest {
        detailsViewModel.processIntent(DetailsIntent.LoadDemoObject(demoObject.demoObjectId.orEmpty(), isUpdated = false))
        runCurrent()
        assertNotNull(detailsViewModel.state.first().demoObjectUI)

        detailsViewModel.processIntent(DetailsIntent.LoadDemoObject(demoObject.demoObjectId.orEmpty(), isUpdated = true))
        assertNull(detailsViewModel.state.first().demoObjectUI)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }
}
