package com.vnstudio.cleanarchitecturedemo.presentation.mapperimpls

import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.mappers.OwnerUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork
import com.vnstudio.cleanarchitecturedemo.domain.models.Owner
import com.vnstudio.cleanarchitecturedemo.presentation.uimodels.ForkUI
import com.vnstudio.cleanarchitecturedemo.presentation.uimodels.OwnerUI

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