package com.vnteam.architecturetemplates.fork_service

import com.vnteam.architecturetemplates.domain.models.Fork

interface ForkService {

    suspend fun insertForks(forks: List<Fork>)

    suspend fun getForks(): List<Fork>?

    suspend fun getForkById(forkId: String): Fork?

    suspend fun deleteForkById(forkId: String)
}