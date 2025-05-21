package benchmarks

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
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
