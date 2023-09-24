package com.vnstudio.cleanarchitecturedemo.database

import androidx.room.Insert
import androidx.room.Query
import com.vnstudio.cleanarchitecturedemo.models.Fork

interface ForkDao {

    @Insert
    fun insertForks(forks: List<Fork>)

    @Query("SELECT * FROM forks")
    fun getForks(): List<Fork>

    @Query("SELECT * FROM forks id = :forkId")
    fun getForkById(forkId: Long): Fork
}