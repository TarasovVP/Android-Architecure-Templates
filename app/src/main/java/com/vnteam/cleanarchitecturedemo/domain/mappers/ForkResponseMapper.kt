package com.vnteam.cleanarchitecturedemo.domain.mappers

import com.vnteam.cleanarchitecturedemo.data.network.responses.ForkResponse
import com.vnteam.cleanarchitecturedemo.domain.models.Fork

interface ForkResponseMapper : BaseMapper<Fork, ForkResponse>