package com.vnstudio.cleanarchitecturedemo.network

import com.vnstudio.cleanarchitecturedemo.models.Fork
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("repos/octocat/Spoon-Knife/forks")
    suspend fun getForks(): Response<List<Fork>>
}