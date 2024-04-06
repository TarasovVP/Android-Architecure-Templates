package com.vnstudio.cleanarchitecturedemo.data.repositoryimpl

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import com.vnstudio.cleanarchitecturedemo.data.network.ApiService
import com.vnstudio.cleanarchitecturedemo.domain.repositories.ApiRepository

class ApiRepositoryImpl(private val apiService: ApiService):
    ApiRepository {

    override suspend fun getForksFromApi(): List<Fork>? {
        return apiService.getForks().body()
    }
}