package architecturetemplates.presentation.mapperimpls

<<<<<<<< HEAD:shared/src/commonMain/kotlin/com/vnteam/architecturetemplates/presentation/mapperimpls/ForkUIMapperImpl.kt
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import presentation.mappers.OwnerUIMapper
========
import architecturetemplates.presentation.mappers.ForkUIMapper
import architecturetemplates.presentation.mappers.OwnerUIMapper
>>>>>>>> 8ed69786 (Implement ios module):app/src/androidMain/kotlin/architecturetemplates/presentation/mapperimpls/ForkUIMapperImpl.kt
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.models.Owner
import architecturetemplates.presentation.uimodels.ForkUI
import architecturetemplates.presentation.uimodels.OwnerUI

class ForkUIMapperImpl(private val owner: OwnerUIMapper) : ForkUIMapper {

    override fun mapToImplModel(from: Fork): ForkUI {
        return ForkUI(
            id = from.id,
            name = from.name,
            fullName = from.fullName,
            owner = owner.mapToImplModel(from.owner ?: Owner()),
            htmlUrl = from.htmlUrl,
            description = from.description
        )
    }

    override fun mapFromImplModel(to: ForkUI): Fork {
        return Fork(
            id = to.id,
            name = to.name,
            fullName = to.fullName,
            owner = owner.mapFromImplModel(to.owner ?: OwnerUI()),
            htmlUrl = to.htmlUrl,
            description = to.description
        )
    }

    override fun mapToImplModelList(fromList: List<Fork>): List<ForkUI> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<ForkUI>): List<Fork> {
        return toList.map { mapFromImplModel(it) }
    }
}