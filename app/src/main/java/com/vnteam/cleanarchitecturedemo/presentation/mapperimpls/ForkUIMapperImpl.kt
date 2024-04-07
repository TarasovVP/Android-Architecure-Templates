package com.vnteam.cleanarchitecturedemo.presentation.mapperimpls

import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnteam.cleanarchitecturedemo.domain.mappers.OwnerUIMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.models.Owner
import com.vnteam.cleanarchitecturedemo.presentation.uimodels.ForkUI
import com.vnteam.cleanarchitecturedemo.presentation.uimodels.OwnerUI

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