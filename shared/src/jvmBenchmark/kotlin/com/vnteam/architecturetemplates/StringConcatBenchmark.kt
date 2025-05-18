package com.vnteam.architecturetemplates

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State

@State(Scope.Benchmark)
class StringConcatBenchmark {
    private lateinit var data: String

    @Setup
    fun prepare() {
        data = DATA
    }

    @Benchmark
    fun plusOperator(): String = data + APPEND

    @Benchmark
    fun stringBuilder(): String =
        buildString {
            append(data)
            append(APPEND)
        }

    private companion object {
        const val DATA = "kotlin"
        const val APPEND = "x"
    }
}
