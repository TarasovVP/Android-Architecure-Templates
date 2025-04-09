package com.vnteam.architecturetemplates.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import secrets.Properties

const val CONNECTION_EXCEPTION = "Connection Exception. Check if the server is running."
const val UNKNOWN_ERROR = "Unknown error"
const val ERROR_STATUS_400 = 400
const val ERROR_STATUS_405 = 405
const val SUCCESS_STATUS_200 = 200
const val SUCCESS_STATUS_299 = 299

suspend inline fun <reified T> HttpResponse?.handleResponse(): NetworkResult<T> {
    return when {
        this == null -> NetworkResult.Failure(UNKNOWN_ERROR)
        status.value in ERROR_STATUS_400..ERROR_STATUS_405 -> {
            println("Error ${bodyAsText()}")
            NetworkResult.Failure(CONNECTION_EXCEPTION)
        }
        status.value !in SUCCESS_STATUS_200..SUCCESS_STATUS_299 -> {
            val error = bodyAsText()
            NetworkResult.Failure(error)
        }

        else -> {
            try {
                val result = body<T>()
                NetworkResult.Success(result)
            } catch (e: Exception) {
                NetworkResult.Failure(e.message ?: UNKNOWN_ERROR)
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.safeRequest(block: HttpClient.() -> HttpResponse): NetworkResult<T> =
    try {
        val response = block()
        response.handleResponse<T>()
    } catch (e: Exception) {
        val errorMessage = if (Properties.CLOUD_URL.contains(":8080/")) CONNECTION_EXCEPTION else e.message
        NetworkResult.Failure(errorMessage)
    }
