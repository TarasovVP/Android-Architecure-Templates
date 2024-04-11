package com.vnteam.architecturetemplates.domain.repositories

import com.vnteam.architecturetemplates.domain.models.Fork
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getForksFromApi(): Flow<List<Fork>?>
}