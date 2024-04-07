package com.vnstudio.cleanarchitecturedemo.data.mapperimpls

import com.vnstudio.cleanarchitecturedemo.data.network.responses.ForkResponse
import com.vnstudio.cleanarchitecturedemo.data.network.responses.OwnerResponse
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork
import com.vnstudio.cleanarchitecturedemo.domain.models.Owner

class ForkResponseMapperImpl(private val ownerResponseMapper: OwnerResponseMapper) :
    ForkResponseMapper {

    override fun mapToImplModel(from: Fork): ForkResponse {
        return ForkResponse(
            id = from.id,
            name = from.name,
            fullName = from.fullName,
            owner = ownerResponseMapper.mapToImplModel(from.owner ?: Owner()),
            htmlUrl = from.htmlUrl,
            description = from.description
        )
    }

    override fun mapFromImplModel(to: ForkResponse): Fork {
        return Fork(
            id = to.id,
            name = to.name,
            fullName = to.fullName,
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