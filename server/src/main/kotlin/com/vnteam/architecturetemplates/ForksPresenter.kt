package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork

interface ForksPresenter {

    suspend fun insertForksToDB(forks: List<Fork>?)

    suspend fun getForksFromDB(): List<Fork>?

    suspend fun getForkById(forkId: String?): Fork?

    suspend fun deleteForkById(forkId: String)
}