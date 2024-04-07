package com.vnstudio.cleanarchitecturedemo.data.mapperimpls

import com.vnstudio.cleanarchitecturedemo.data.network.responses.OwnerResponse
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Owner

class OwnerResponseMapperImpl : OwnerResponseMapper {

    override fun mapToImplModel(from: Owner): OwnerResponse {
        return OwnerResponse(login = from.login,
        ownerId = from.ownerId,
        avatarUrl = from.avatarUrl)
    }

    override fun mapFromImplModel(to: OwnerResponse): Owner {
        return Owner(login = to.login,
            ownerId = to.ownerId,
            avatarUrl = to.avatarUrl)
    }

    override fun mapToImplModelList(fromList: List<Owner>): List<OwnerResponse> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<OwnerResponse>): List<Owner> {
        return toList.map { mapFromImplModel(it) }
    }
}