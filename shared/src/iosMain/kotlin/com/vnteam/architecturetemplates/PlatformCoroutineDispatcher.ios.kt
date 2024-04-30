package com.vnteam.architecturetemplates

import kotlinx.coroutines.*
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

import kotlinx.coroutines.CoroutineDispatcher

class MainDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            block.run()
        }
    }
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformCoroutineDispatcher actual constructor() {
    actual val io: CoroutineDispatcher = Dispatchers.Default
    actual val main: CoroutineDispatcher = MainDispatcher()
    actual val default: CoroutineDispatcher = Dispatchers.Default
}