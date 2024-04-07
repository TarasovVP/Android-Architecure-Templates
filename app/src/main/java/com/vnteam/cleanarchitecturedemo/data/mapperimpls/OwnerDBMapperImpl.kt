package com.vnteam.cleanarchitecturedemo.data.mapperimpls

import com.vnteam.cleanarchitecturedemo.data.database.entities.OwnerDB
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerDBMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Owner

class OwnerDBMapperImpl : OwnerDBMapper {

    override fun mapToImplModel(from: Owner): OwnerDB {
        return OwnerDB(login = from.login,
        ownerId = from.ownerId,
        avatarUrl = from.avatarUrl)
    }

    override fun mapFromImplModel(to: OwnerDB): Owner {
        return Owner(login = to.login,
            ownerId = to.ownerId,
            avatarUrl = to.avatarUrl)
    }

    override fun mapToImplModelList(fromList: List<Owner>): List<OwnerDB> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<OwnerDB>): List<Owner> {
        return toList.map { mapFromImplModel(it) }
    }
}