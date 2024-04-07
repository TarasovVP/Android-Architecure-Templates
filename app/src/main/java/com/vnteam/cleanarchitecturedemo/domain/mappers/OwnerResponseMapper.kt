package com.vnteam.cleanarchitecturedemo.domain.mappers

import com.vnteam.cleanarchitecturedemo.data.network.responses.OwnerResponse
import com.vnteam.cleanarchitecturedemo.domain.models.Owner

interface OwnerResponseMapper : BaseMapper<Owner, OwnerResponse>