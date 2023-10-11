package com.vnstudio.cleanarchitecturedemo.data.network

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("repos/octocat/Spoon-Knife/forks")
    suspend fun getForks(): Response<List<Fork>>
}