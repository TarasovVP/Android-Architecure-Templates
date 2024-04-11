package com.vnteam.architecturetemplates.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vnteam.architecturetemplates.models.Fork

@Dao
interface ForkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForks(forks: List<Fork>)

    @Query("SELECT * FROM forks")
    fun getForks(): List<Fork>

    @Query("SELECT * FROM forks WHERE :id = id")
    fun getForkById(id: Long): Fork
}