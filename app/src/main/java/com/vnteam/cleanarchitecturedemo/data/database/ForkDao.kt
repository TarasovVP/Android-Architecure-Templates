package com.vnteam.cleanarchitecturedemo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vnteam.cleanarchitecturedemo.data.database.entities.ForkDB

@Dao
interface ForkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForks(forks: List<ForkDB>)

    @Query("SELECT * FROM forks")
    fun getForks(): List<ForkDB>

    @Query("SELECT * FROM forks WHERE :id = id")
    fun getForkById(id: Long): ForkDB
}