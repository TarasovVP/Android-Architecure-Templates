package com.vnteam.architecturetemplates.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

suspend inline fun <reified T> HttpResponse?.handleResponse(): NetworkResult<T> {
    return when {
        this == null -> NetworkResult.Failure("Unknown error")
        status.value !in 200..299 -> {
            val error = bodyAsText()
            NetworkResult.Failure(error)
        }

        else -> {
            try {
                val result = body<T>()
                NetworkResult.Success(result)
            } catch (e: Exception) {
                NetworkResult.Failure(e.message)
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.safeRequest(
    block: suspend HttpClient.() -> HttpResponse,
): NetworkResult<T> =
    try {
        val response = request { block() }
        response.handleResponse<T>()
    } catch (e: Exception) {
        val errorMessage = if (e.message.isNullOrEmpty()) "Connection Exception. Check if the server is running." else e.message
        NetworkResult.Failure(errorMessage)
    }
