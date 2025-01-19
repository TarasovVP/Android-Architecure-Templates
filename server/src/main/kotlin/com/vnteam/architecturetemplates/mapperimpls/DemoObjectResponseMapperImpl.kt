package com.vnteam.architecturetemplates.mapperimpls

import com.vnteam.architecturetemplates.responses.DemoObjectResponse
import com.vnteam.architecturetemplates.mappers.DemoObjectResponseMapper
import com.vnteam.architecturetemplates.mappers.OwnerResponseMapper
import com.vnteam.architecturetemplates.models.DemoObject
import com.vnteam.architecturetemplates.models.Owner
import com.vnteam.architecturetemplates.responses.OwnerResponse

class DemoObjectResponseMapperImpl(private val ownerResponseMapper: OwnerResponseMapper) :
    DemoObjectResponseMapper {

    override fun mapToImplModel(from: DemoObject): DemoObjectResponse {
        return DemoObjectResponse(
            demoObjectId = from.demoObjectId,
            name = from.name,
            owner = ownerResponseMapper.mapToImplModel(from.owner ?: Owner()),
            htmlUrl = from.htmlUrl,
            description = from.description
        )
    }

    override fun mapFromImplModel(to: DemoObjectResponse): DemoObject {
        return DemoObject(
            demoObjectId = to.demoObjectId,
            name = to.name,
            owner = ownerResponseMapper.mapFromImplModel(to.owner ?: OwnerResponse()),
            htmlUrl = to.htmlUrl,
            description = to.description
        )
    }

    override fun mapToImplModelList(fromList: List<DemoObject>): List<DemoObjectResponse> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<DemoObjectResponse>): List<DemoObject> {
        return toList.map { mapFromImplModel(it) }
    }
}