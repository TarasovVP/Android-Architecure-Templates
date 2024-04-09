package com.vnteam.cleanarchitecturedemo.data.mapperimpls

import com.vnteam.cleanarchitecturedemo.ForkWithOwner
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.models.Owner

class ForkDBMapperImpl : ForkDBMapper {

    override fun mapToImplModel(from: Fork): ForkWithOwner {
        return ForkWithOwner(id = from.id ?: 0,
            name = from.name,
            fullName = from.fullName,
            ownerId = from.owner?.ownerId,
            login = from.owner?.login,
            avatarUrl = from.owner?.avatarUrl,
            htmlUrl = from.htmlUrl,
            description = from.description)
    }

    override fun mapFromImplModel(to: ForkWithOwner): Fork {
        return Fork(id = to.id,
        name = to.name,
        fullName = to.fullName,
        owner = Owner(ownerId = to.ownerId,
            login = to.login,
            avatarUrl = to.avatarUrl),
        htmlUrl = to.htmlUrl,
        description = to.description)
    }

    override fun mapToImplModelList(fromList: List<Fork>): List<ForkWithOwner> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<ForkWithOwner>): List<Fork> {
        return toList.map { mapFromImplModel(it) }
    }
}