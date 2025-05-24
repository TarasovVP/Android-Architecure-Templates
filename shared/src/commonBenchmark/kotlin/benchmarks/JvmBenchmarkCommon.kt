package benchmarks

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Measurement
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup
import kotlin.math.cos
import kotlin.math.sqrt

const val WARMUP_ITERATIONS = 5

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(iterations = WARMUP_ITERATIONS, time = 1, timeUnit = BenchmarkTimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = BenchmarkTimeUnit.SECONDS)
class JvmBenchmarkCommon {
    private var data = DEFAULT_DATA

    @Setup
    fun setUp() {
        data = NEW_DATA
    }

    @Benchmark
    fun sqrtBenchmark(): Double {
        return sqrt(data)
    }

    @Benchmark
    fun cosBenchmark(): Double {
        return cos(data)
    }

    private companion object {
        const val DEFAULT_DATA = 0.0
        const val NEW_DATA = 3.0
    }
}
