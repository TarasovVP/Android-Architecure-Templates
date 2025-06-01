package benchmarks

import com.vnteam.architecturetemplates.data.mapperimpls.DemoObjectResponseMapperImpl
import com.vnteam.architecturetemplates.domain.mappers.OwnerUIMapper
import com.vnteam.architecturetemplates.domain.models.DemoObject
import com.vnteam.architecturetemplates.domain.models.Owner
import com.vnteam.architecturetemplates.domain.responses.DemoObjectResponse
import com.vnteam.architecturetemplates.presentation.mapperimpls.DemoObjectUIMapperImpl
import com.vnteam.architecturetemplates.presentation.uimodels.DemoObjectUI
import com.vnteam.architecturetemplates.presentation.uimodels.OwnerUI
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.Setup
import kotlinx.benchmark.State

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.MICROSECONDS) // Используем микросекунды для мапперов
class MappersBenchmark {
    private lateinit var testObject: DemoObject
    private lateinit var testObjectList: List<DemoObject>

    private lateinit var responseMapper: DemoObjectResponseMapperImpl
    private lateinit var uiMapper: DemoObjectUIMapperImpl

    @Setup
    fun setup() {
        responseMapper = DemoObjectResponseMapperImpl(OwnerResponseMapperStub())
        uiMapper = DemoObjectUIMapperImpl(OwnerUIMapperStub())

        testObject = MapperTestData.createTestDemoObject()
        testObjectList = MapperTestData.createTestDemoObjectList(100)
    }

    @Benchmark
    fun singleObjectTransformationChain(): DemoObjectUI = runBlocking {
        val response = responseMapper.mapToImplModel(testObject)
        uiMapper.mapToImplModel(responseMapper.mapFromImplModel(response))
    }

    @Benchmark
    fun listTransformationChain(): List<DemoObjectUI> = runBlocking {
        val responses = responseMapper.mapToImplModelList(testObjectList)
        uiMapper.mapToImplModelList(responseMapper.mapFromImplModelList(responses))
    }

    @Benchmark
    fun responseMapperSingle(): DemoObjectResponse = runBlocking {
        responseMapper.mapToImplModel(testObject)
    }

    @Benchmark
    fun responseMapperList(): List<DemoObjectResponse> = runBlocking {
        responseMapper.mapToImplModelList(testObjectList)
    }

    @Benchmark
    fun uiMapperSingle(): DemoObjectUI = runBlocking {
        uiMapper.mapToImplModel(testObject)
    }

    @Benchmark
    fun uiMapperList(): List<DemoObjectUI> = runBlocking {
        uiMapper.mapToImplModelList(testObjectList)
    }
}

private class OwnerResponseMapperStub : OwnerResponseMapper {
    override fun mapToImplModel(from: Owner): OwnerResponse = OwnerResponse(
        ownerId = from.ownerId,
        login = from.login,
        avatarUrl = from.avatarUrl,
        htmlUrl = from.htmlUrl
    )

    override fun mapFromImplModel(to: OwnerResponse): Owner = Owner(
        ownerId = to.ownerId,
        login = to.login,
        avatarUrl = to.avatarUrl,
        htmlUrl = to.htmlUrl
    )
}

private class OwnerUIMapperStub : OwnerUIMapper {
    override fun mapToImplModel(from: Owner): OwnerUI = OwnerUI(
        ownerId = from.ownerId,
        login = from.login,
        avatarUrl = from.avatarUrl,
        htmlUrl = from.htmlUrl
    )

    override fun mapFromImplModel(to: OwnerUI): Owner = Owner(
        ownerId = to.ownerId,
        login = to.login,
        avatarUrl = to.avatarUrl,
        htmlUrl = to.htmlUrl
    )
}