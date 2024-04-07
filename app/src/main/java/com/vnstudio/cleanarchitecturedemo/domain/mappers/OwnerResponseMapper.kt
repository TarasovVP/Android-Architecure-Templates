package com.vnstudio.cleanarchitecturedemo.domain.mappers

import com.vnstudio.cleanarchitecturedemo.data.network.responses.OwnerResponse
import com.vnstudio.cleanarchitecturedemo.domain.models.Owner

interface OwnerResponseMapper : BaseMapper<Owner, OwnerResponse>