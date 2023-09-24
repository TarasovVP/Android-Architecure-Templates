package com.vnstudio.cleanarchitecturedemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vnstudio.cleanarchitecturedemo.models.Fork

@Dao
interface ForkDao {

    @Insert
    fun insertForks(forks: List<Fork>)

    @Query("SELECT * FROM forks")
    fun getForks(): List<Fork>

    @Query("SELECT * FROM forks WHERE :id = id")
    fun getForkById(id: Long): Fork
}