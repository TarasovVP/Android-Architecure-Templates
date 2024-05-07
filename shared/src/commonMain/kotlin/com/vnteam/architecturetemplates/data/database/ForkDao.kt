package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.ForkWithOwner

interface ForkDao {

    suspend fun insertForkWithOwners(forks: List<ForkWithOwner>)

    suspend fun getForks(forkWithOwners: (List<ForkWithOwner>) -> Unit)

    suspend fun getForkById(id: Long, forkWithOwner: (ForkWithOwner?) -> Unit)
}