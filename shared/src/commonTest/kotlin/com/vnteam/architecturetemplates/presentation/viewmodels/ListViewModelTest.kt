package com.vnteam.architecturetemplates.presentation.viewmodels

import com.vnteam.architecturetemplates.di.testModule
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.ClearDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.DeleteDemoObjectUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromApiUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromDBUseCase
import com.vnteam.architecturetemplates.domain.usecase.InsertDemoObjectsUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeClearDemoObjectsUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeDeleteDemoObjectUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeGetDemoObjectsFromApiUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeGetDemoObjectsFromDBUseCase
import com.vnteam.architecturetemplates.fake.domain.usecaseimpl.FakeInsertDemoObjectsUseCase
import com.vnteam.architecturetemplates.injectAs
import com.vnteam.architecturetemplates.presentation.intents.ListIntent
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.inject
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class ListViewModelTest : BaseViewModelTest() {

    private val listViewModel by inject<ListViewModel>()

    private val fakeClearUseCase by injectAs<ClearDemoObjectUseCase, FakeClearDemoObjectsUseCase>()
    private val fakeGetDBUseCase by injectAs<GetDemoObjectsFromDBUseCase, FakeGetDemoObjectsFromDBUseCase>()
    private val fakeGetApiUseCase by injectAs<GetDemoObjectsFromApiUseCase, FakeGetDemoObjectsFromApiUseCase>()
    private val fakeInsertUseCase by injectAs<InsertDemoObjectsUseCase, FakeInsertDemoObjectsUseCase>()
    private val fakeDeleteUseCase by injectAs<DeleteDemoObjectUseCase, FakeDeleteDemoObjectUseCase>()

    private val demoObject = DemoObject("1", "DemoObj")

    @BeforeTest
    override fun setup() {
        super.setup()
        startKoin {
            modules(
                testModule + module {
                    single<ClearDemoObjectUseCase> { FakeClearDemoObjectsUseCase() }
                    single<GetDemoObjectsFromDBUseCase> { FakeGetDemoObjectsFromDBUseCase() }
                    single<GetDemoObjectsFromApiUseCase> { FakeGetDemoObjectsFromApiUseCase() }
                    single<InsertDemoObjectsUseCase> { FakeInsertDemoObjectsUseCase() }
                    single<DeleteDemoObjectUseCase> { FakeDeleteDemoObjectUseCase() }
                }
            )
        }
    }

    @Test
    fun testClearDemoObjects() = runTest {
        listViewModel.processIntent(ListIntent.ClearDemoObjects())
        runCurrent()

        assertTrue(fakeClearUseCase.isExecuteCalled)
        val finalState = listViewModel.state.first()
        assertTrue(finalState.clearSuccessResult)
    }

    @Test
    fun testLoadDemoObjects() = runTest {
        fakeGetApiUseCase.demoObjects = listOf(demoObject)
        fakeGetDBUseCase.demoObjects = listOf(demoObject)
        listViewModel.processIntent(ListIntent.LoadDemoObjects(isInit = true))
        runCurrent()


        assertEquals(listOf(demoObject), fakeInsertUseCase.demoObjects)
        val expectedDemoObject = DemoObjectUI(demoObject.demoObjectId, demoObject.name, OwnerUI())
        val state = listViewModel.state.first()
        assertEquals(listOf(expectedDemoObject), state.demoObjectUIs)
    }

    @Test
    fun testDeleteDemoObject() = runTest {
        listViewModel.processIntent(ListIntent.DeleteDemoObject(demoObject.demoObjectId.orEmpty()))
        runCurrent()

        assertTrue(fakeDeleteUseCase.isExecuteCalled)
        val finalState = listViewModel.state.first()
        assertTrue(finalState.successResult)
    }
}
