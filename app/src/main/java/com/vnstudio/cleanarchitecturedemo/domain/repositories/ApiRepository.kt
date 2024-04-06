package com.vnstudio.cleanarchitecturedemo.domain.repositories

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork

interface ApiRepository {

    suspend fun getForksFromApi(): List<Fork>?
}