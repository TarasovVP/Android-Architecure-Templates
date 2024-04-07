package com.vnteam.cleanarchitecturedemo.domain.repositories

import com.vnteam.cleanarchitecturedemo.domain.models.Fork

interface DBRepository {

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB(): List<Fork>

    fun getForkById(forkId: Long): Fork
}