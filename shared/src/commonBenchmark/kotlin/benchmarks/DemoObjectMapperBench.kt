package benchmarks

import com.vnteam.architecturetemplates.domain.mappers.DemoObjectUIMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.presentation.mapperimpls.DemoObjectUIMapperImpl
import com.vnteam.architecturetemplates.presentation.mapperimpls.OwnerUIMapperImpl
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Param
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State

@State(Scope.Benchmark)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
class DemoObjectMapperBench {
    @Param("1", "100", "1000")
    var listSize: Int = 1

    val ownerUIMapper: OwnerUIMapper = OwnerUIMapperImpl()
    private val mapper: DemoObjectUIMapper = DemoObjectUIMapperImpl(OwnerUIMapperImpl())
    private lateinit var source: List<DemoObject>
    private lateinit var target: List<DemoObjectUI>

    @Setup
    fun generateData() {
        source =
            List(listSize) { idx ->
                DemoObject(
                    demoObjectId = idx.toString(),
                    name = "Obj$idx",
                    owner = Owner(idx.toString(), "login$idx"),
                    htmlUrl = "https://demo/$idx",
                    description = if (idx % DEFAULT_INT == 0) "Desc$idx" else null,
                )
            }
        target = mapper.mapToImplModelList(source)
    }

    @Benchmark
    fun mapToList() = mapper.mapToImplModelList(source)

    @Benchmark
    fun mapFromList() = mapper.mapFromImplModelList(target)

    private companion object {
        const val DEFAULT_INT = 2
    }
}
