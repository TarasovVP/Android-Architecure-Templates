package com.vnteam.cleanarchitecturedemo.domain.repositories

import com.vnteam.cleanarchitecturedemo.domain.models.Fork

interface ApiRepository {

    suspend fun getForksFromApi(): List<Fork>?
}