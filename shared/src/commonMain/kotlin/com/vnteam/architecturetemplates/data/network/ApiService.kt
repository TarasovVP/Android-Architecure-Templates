package com.vnteam.architecturetemplates.data.network

import config.BuildConfig.GITHUB_TOKEN
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.HttpResponse


class ApiService(
    private val baseUrl: String,
    private val httpClient: HttpClient,
) {

    suspend fun getForks(): HttpResponse {

        val httpResponse = try {
            httpClient.get("${baseUrl}repos/octocat/Spoon-Knife/forks") {
                /*headers {
                    append("Accept", "application/vnd.github+json")
                    append("Authorization", "Bearer $GITHUB_TOKEN")
                    append("X-GitHub-Api-Version", "2022-11-28")
                    append("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                }*/
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return httpResponse
    }
}