package com.vnteam.cleanarchitecturedemo.data.repositoryimpl

import com.vnteam.cleanarchitecturedemo.data.network.ApiService
import com.vnteam.cleanarchitecturedemo.data.network.responses.ForkResponse
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.repositories.ApiRepository
import io.ktor.client.call.body

class ApiRepositoryImpl(private val apiService: ApiService, private val forkResponseMapper: ForkResponseMapper) :
    ApiRepository {

    override suspend fun getForksFromApi(): List<Fork> {
        return forkResponseMapper.mapFromImplModelList(apiService.getForks()?.body<List<ForkResponse>>().orEmpty())
    }
}