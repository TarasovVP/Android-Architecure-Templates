package com.vnstudio.cleanarchitecturedemo.domain.mappers

import com.vnstudio.cleanarchitecturedemo.data.network.responses.ForkResponse
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork

interface ForkResponseMapper : BaseMapper<Fork, ForkResponse>