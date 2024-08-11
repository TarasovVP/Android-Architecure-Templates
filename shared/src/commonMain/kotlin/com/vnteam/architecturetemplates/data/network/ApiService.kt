package com.vnteam.architecturetemplates.data.network

import com.vnteam.architecturetemplates.domain.responses.ForkResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ApiService(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) {

    suspend fun insertForksToApi(forks: List<ForkResponse>): NetworkResult<Unit> {
        return httpClient.safeRequest<Unit> {
            httpClient.post("${baseUrl}forks") {
                contentType(ContentType.Application.Json)
                setBody(forks)
            }
        }
    }

    suspend fun getForksFromApi(): NetworkResult<List<ForkResponse>> {
        return httpClient.safeRequest<List<ForkResponse>> {
            get("${baseUrl}forks") {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getForkById(id: String): NetworkResult<ForkResponse> {
        return httpClient.safeRequest<ForkResponse> {
            httpClient.get("${baseUrl}forks/$id") {
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun deleteForkById(id: String): NetworkResult<Unit> {
        return httpClient.safeRequest<Unit> {
            httpClient.delete("${baseUrl}forks/$id") {
                contentType(ContentType.Application.Json)
            }
        }
    }
}