package com.vnstudio.cleanarchitecturedemo.domain.repositories

import com.vnstudio.cleanarchitecturedemo.domain.models.Fork

interface DBRepository {

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): List<Fork>

    fun getForkById(forkId: Long): Fork
}