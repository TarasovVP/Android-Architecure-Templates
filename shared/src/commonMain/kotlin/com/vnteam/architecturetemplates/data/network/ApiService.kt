package com.vnteam.architecturetemplates.data.network

import com.vnteam.architecturetemplates.domain.models.Fork
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI


class ApiService(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) {

    @OptIn(InternalAPI::class)
    suspend fun insertForksToDB(forks: List<Fork>): HttpResponse {
        val httpResponse = try {
            httpClient.post("${baseUrl}forks") {
                contentType(ContentType.Application.Json)
                body = forks
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return httpResponse
    }

    suspend fun getForksFromApi(): HttpResponse {
        val httpResponse = try {
            httpClient.get("${baseUrl}forks") {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return httpResponse
    }

    suspend fun getForkById(id: Long): HttpResponse {
        val httpResponse = try {
            httpClient.get("${baseUrl}forks/$id") {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return httpResponse
    }

    suspend fun deleteForkById(id: Long): HttpResponse {
        val httpResponse = try {
            httpClient.delete("${baseUrl}forks/$id") {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return httpResponse
    }
}