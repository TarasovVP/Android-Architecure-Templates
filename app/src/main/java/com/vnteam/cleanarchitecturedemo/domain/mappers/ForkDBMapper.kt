package com.vnteam.cleanarchitecturedemo.domain.mappers

import com.vnteam.cleanarchitecturedemo.ForkWithOwner
import com.vnteam.cleanarchitecturedemo.domain.models.Fork

interface ForkDBMapper : BaseMapper<Fork, ForkWithOwner>