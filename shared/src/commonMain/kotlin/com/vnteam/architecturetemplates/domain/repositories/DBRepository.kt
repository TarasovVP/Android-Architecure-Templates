package com.vnteam.architecturetemplates.domain.repositories

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): Flow<List<Fork>>

    fun getForkById(forkId: Long): Flow<Fork?>
}