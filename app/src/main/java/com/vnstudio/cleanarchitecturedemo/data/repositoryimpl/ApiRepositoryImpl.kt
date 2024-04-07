package com.vnstudio.cleanarchitecturedemo.data.repositoryimpl

import com.vnstudio.cleanarchitecturedemo.data.network.ApiService
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnstudio.cleanarchitecturedemo.domain.models.Fork
import com.vnstudio.cleanarchitecturedemo.domain.repositories.ApiRepository

class ApiRepositoryImpl(private val apiService: ApiService, private val forkResponseMapper: ForkResponseMapper) :
    ApiRepository {

    override suspend fun getForksFromApi(): List<Fork> {
        return forkResponseMapper.mapFromImplModelList(apiService.getForks().body().orEmpty())
    }
}