package com.vnteam.architecturetemplates

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class PlatformCoroutineDispatcher actual constructor() {
    actual val io: CoroutineDispatcher = Dispatchers.IO
    actual val main: CoroutineDispatcher = Dispatchers.Main
    actual val default: CoroutineDispatcher = Dispatchers.Default
}