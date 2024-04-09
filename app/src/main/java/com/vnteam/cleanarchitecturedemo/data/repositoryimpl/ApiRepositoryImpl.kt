package com.vnteam.cleanarchitecturedemo.data.repositoryimpl

import com.vnteam.cleanarchitecturedemo.data.network.ApiService
import com.vnteam.cleanarchitecturedemo.data.network.NetworkResult
import com.vnteam.cleanarchitecturedemo.data.network.handleResponse
import com.vnteam.cleanarchitecturedemo.data.network.responses.ForkResponse
import com.vnteam.cleanarchitecturedemo.domain.mappers.ForkResponseMapper
import com.vnteam.cleanarchitecturedemo.domain.models.Fork
import com.vnteam.cleanarchitecturedemo.domain.repositories.ApiRepository

class ApiRepositoryImpl(private val apiService: ApiService, private val forkResponseMapper: ForkResponseMapper) :
    ApiRepository {

    override suspend fun getForksFromApi(): List<Fork> {
        when (val response = apiService.getForks().handleResponse<List<ForkResponse>>()) {
            is NetworkResult.Success -> {
                return response.data?.map { forkResponseMapper.mapFromImplModel(it) }.orEmpty()
            }
            is NetworkResult.Failure -> {
                throw Exception(response.errorMessage)
            }
        }
    }
}