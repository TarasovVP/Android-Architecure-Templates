package com.vnteam.cleanarchitecturedemo.domain.repositories

import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface DBRepository {

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): Flow<List<Fork>>

    fun getForkById(forkId: Long): Flow<Fork?>
}