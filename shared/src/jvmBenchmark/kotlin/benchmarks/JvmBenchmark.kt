package benchmarks

import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.benchmark.Warmup
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Fork
import org.openjdk.jmh.annotations.Measurement
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.annotations.OutputTimeUnit
import org.openjdk.jmh.runner.Defaults.WARMUP_ITERATIONS
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
// jvmBenchmark can access declarations from jvmMain!
@Warmup(iterations = WARMUP_ITERATIONS, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
class JvmBenchmark {
    private var data = DEFAULT_DATA

    @Setup
    fun setUp() {
        data = NEW_DATA
    }

    @Benchmark
    fun sqrtBenchmark(): Double {
        return Math.sqrt(data)
    }

    @Benchmark
    fun cosBenchmark(): Double {
        return Math.cos(data)
    }

    private companion object {
        const val DEFAULT_DATA = 0.0
        const val NEW_DATA = 3.0
    }
}
