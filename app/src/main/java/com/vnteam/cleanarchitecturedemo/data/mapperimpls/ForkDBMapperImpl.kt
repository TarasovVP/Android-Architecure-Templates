package com.vnteam.cleanarchitecturedemo.data.mapperimpls

import com.vnteam.cleanarchitecturedemo.data.database.entities.ForkDB
import com.vnteam.cleanarchitecturedemo.data.database.entities.OwnerDB
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkDBMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerDBMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.models.Owner

class ForkDBMapperImpl(private val ownerDBMapper: OwnerDBMapper) : ForkDBMapper {

    override fun mapToImplModel(from: Fork): ForkDB {
        return ForkDB(id = from.id,
            name = from.name,
            fullName = from.fullName,
            owner = ownerDBMapper.mapToImplModel(from.owner ?: Owner()),
            htmlUrl = from.htmlUrl,
            description = from.description)
    }

    override fun mapFromImplModel(to: ForkDB): Fork {
        return Fork(id = to.id,
        name = to.name,
        fullName = to.fullName,
        owner = ownerDBMapper.mapFromImplModel(to.owner ?: OwnerDB()),
        htmlUrl = to.htmlUrl,
        description = to.description)
    }

    override fun mapToImplModelList(fromList: List<Fork>): List<ForkDB> {
        return fromList.map { mapToImplModel(it) }
    }

    override fun mapFromImplModelList(toList: List<ForkDB>): List<Fork> {
        return toList.map { mapFromImplModel(it) }
    }
}