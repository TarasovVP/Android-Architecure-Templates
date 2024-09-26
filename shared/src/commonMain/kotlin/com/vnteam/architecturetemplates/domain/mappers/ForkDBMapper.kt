package com.vnteam.architecturetemplates.domain.mappers

import com.vnteam.architecturetemplates.ForkWithOwner
import com.vnteam.architecturetemplates.domain.models.Fork

interface ForkDBMapper : BaseMapper<Fork, ForkWithOwner>