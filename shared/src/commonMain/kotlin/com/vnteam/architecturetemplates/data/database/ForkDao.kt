package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.ForkWithOwner
import kotlinx.coroutines.flow.Flow

interface ForkDao {

    suspend fun clearForks()

    suspend fun insertForkWithOwners(forks: List<ForkWithOwner>)

    suspend fun getForkWithOwners(): Flow<List<ForkWithOwner>>

    suspend fun getForkById(id: String): Flow<ForkWithOwner?>

    suspend fun deleteForkById(id: String)
}