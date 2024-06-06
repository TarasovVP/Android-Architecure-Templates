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

    override suspend fun insertForksToApi(forks: List<Fork>?): Flow<Unit> {
        when (val response = apiService.insertForksToDB(forks.orEmpty()).handleResponse<Unit>()) {
            is NetworkResult.Success -> {
                return flowOf( Unit )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun getForksFromApi(): Flow<List<Fork>> {
        when (val response = apiService.getForksFromApi().handleResponse<List<ForkResponse>>()) {
            is NetworkResult.Success -> {
                return flowOf( response.data?.map { forkResponseMapper.mapFromImplModel(it) }.orEmpty() )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun getForkById(forkId: Long?): Flow<Fork?> {
        when (val response = apiService.getForkById(forkId ?: 0).handleResponse<ForkResponse>()) {
            is NetworkResult.Success -> {
                return flowOf( response.data?.let { forkResponseMapper.mapFromImplModel(it) } )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }

    override suspend fun deleteForkById(forkId: Long): Flow<Unit> {
        when (val response = apiService.deleteForkById(forkId).handleResponse<List<ForkResponse>>()) {
            is NetworkResult.Success -> {
                return flowOf( Unit )
            }
            is NetworkResult.Failure -> {
                println(response.errorMessage)
                throw Exception(response.errorMessage)
            }
        }
    }
}