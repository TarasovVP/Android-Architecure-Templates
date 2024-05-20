package com.vnteam.architecturetemplates.data.repositoryimpl

import com.vnteam.architecturetemplates.data.network.ApiService
import com.vnteam.architecturetemplates.data.network.NetworkResult
import com.vnteam.architecturetemplates.data.network.handleResponse
import com.vnteam.architecturetemplates.data.network.responses.ForkResponse
import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.domain.repositories.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ApiRepositoryImpl(private val apiService: ApiService, private val forkResponseMapper: ForkResponseMapper) :
    ApiRepository {

    override suspend fun getForksFromApi(): Flow<List<Fork>> {
        when (val response = apiService.getForks().handleResponse<List<ForkResponse>>()) {
            is NetworkResult.Success -> {
                return flowOf( response.data?.map { forkResponseMapper.mapFromImplModel(it) }.orEmpty() )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }
}