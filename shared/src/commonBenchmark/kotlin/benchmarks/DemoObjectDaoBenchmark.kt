package benchmarks

import com.vnteam.architecturetemplates.DemoObjectWithOwner
import com.vnteam.architecturetemplates.data.database.DemoObjectDaoImpl
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
class DemoObjectDaoBenchmark {
    private lateinit var dao: DemoObjectDaoImpl
    private lateinit var testData: List<DemoObjectWithOwner>

    @Setup
    fun setup() {
        val driver = createTestSqlDriver()
        val database = createSharedDatabase(driver)
        dao = DemoObjectDaoImpl(database)

        testData =
            List(100) { index ->
                DemoObjectWithOwner(
                    id = index.toLong(),
                    demoObjectId = "id_$index",
                    name = "Object $index",
                    ownerId = "owner_$index",
                    login = "user$index",
                    avatarUrl = "https://example.com/avatar$index.jpg",
                    htmlUrl = "https://example.com/user$index",
                    description = "Description $index",
                    url = "https://example.com/object$index"
                )
            }
    }

    @Benchmark
    fun insert100DemoObjects(): Unit =
        runBlocking {
            dao.insertDemoObjectWithOwners(testData)
        }

    @Benchmark
    fun getAllDemoObjects(): List<DemoObjectWithOwner> =
        runBlocking {
            dao.getDemoObjectWithOwners().first()
        }

    @Benchmark
    fun getDemoObjectById(): DemoObjectWithOwner? =
        runBlocking {
            dao.getDemoObjectById("id_50").first()
        }

    @Benchmark
    fun deleteDemoObjectById(): Unit =
        runBlocking {
            dao.deleteDemoObjectById("id_50")
        }

    @Benchmark
    fun clearAllDemoObjects(): Unit =
        runBlocking {
            dao.clearDemoObjects()
        }

    @Benchmark
    fun fullCrudCycle(): Unit =
        runBlocking {
            dao.insertDemoObjectWithOwners(testData)

            val all = dao.getDemoObjectWithOwners().first()
            val single = dao.getDemoObjectById("id_50").first()

            dao.deleteDemoObjectById("id_50")
            dao.clearDemoObjects()
        }
}
