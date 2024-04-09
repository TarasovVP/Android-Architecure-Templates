package com.vnteam.cleanarchitecturedemo.domain.repositories

import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getForksFromApi(): Flow<List<Fork>?>
}