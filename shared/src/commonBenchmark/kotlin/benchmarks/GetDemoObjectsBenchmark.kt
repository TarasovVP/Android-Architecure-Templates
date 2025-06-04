package benchmarks

import kotlinx.coroutines.runBlocking
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromApiUseCase
import com.vnteam.architecturetemplates.domain.usecase.GetDemoObjectsFromDBUseCase
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
class GetDemoObjectsBenchmark {

    private lateinit var apiUseCase: GetDemoObjectsFromApiUseCase
    private lateinit var dbUseCase : GetDemoObjectsFromDBUseCase

    @Setup
    fun setUp() {
        val testData: List<DemoObject> = MapperTestData.createTestDemoObjectList(100)

        apiUseCase = FakeGetDemoObjectsFromApiUseCase().apply {
            demoObjects = testData
        }
        dbUseCase  = FakeGetDemoObjectsFromDBUseCase().apply {
            demoObjects = testData
        }
    }

    @Benchmark
    fun fromApi(): List<DemoObject> = runBlocking {
        apiUseCase.execute(null)
    }

    @Benchmark
    fun fromDb(): List<DemoObject>? = runBlocking {
        dbUseCase.execute(null).first()
    }
}