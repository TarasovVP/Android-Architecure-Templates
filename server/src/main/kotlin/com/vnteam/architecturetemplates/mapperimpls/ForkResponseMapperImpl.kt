package com.vnteam.architecturetemplates.mapperimpls

import com.vnteam.architecturetemplates.domain.responses.ForkResponse
import com.vnteam.architecturetemplates.domain.responses.OwnerResponse
import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.mappers.OwnerResponseMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.models.Owner

class ForkResponseMapperImpl(private val ownerResponseMapper: OwnerResponseMapper) :
    ForkResponseMapper {

    override fun mapToImplModel(from: Fork): ForkResponse {
        return ForkResponse(
            forkId = from.forkId,
            name = from.name,
            owner = ownerResponseMapper.mapToImplModel(from.owner ?: Owner()),
            htmlUrl = from.htmlUrl,
            description = from.description
        )
    }

    override fun mapFromImplModel(to: ForkResponse): Fork {
        return Fork(
            forkId = to.forkId,
            name = to.name,
            owner = ownerResponseMapper.mapFromImplModel(to.owner ?: OwnerResponse()),
            htmlUrl = to.htmlUrl,
            description = to.description
        )
    }

    override fun mapToImplModelList(fromList: List<Fork>): List<ForkResponse> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<ForkResponse>): List<Fork> {
        return toList.map { mapFromImplModel(it) }
    }
}