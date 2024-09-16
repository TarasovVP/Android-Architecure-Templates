<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/data/database/ForkDao.kt
package com.vnteam.architecturetemplates.data.database
========
package com.vnteam.architecturetemplates.database
>>>>>>>> 13d1264 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/database/ForkDao.kt

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/data/database/ForkDao.kt
import com.vnteam.architecturetemplates.data.database.entities.ForkDB
========
import com.vnteam.architecturetemplates.models.Fork
>>>>>>>> 13d1264 (Rename project):app/src/main/java/com/vnteam/architecturetemplates/database/ForkDao.kt

@Dao
interface ForkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForks(forks: List<ForkDB>)

    @Query("SELECT * FROM forks")
    fun getForks(): List<ForkDB>

    @Query("SELECT * FROM forks WHERE :id = id")
    fun getForkById(id: Long): ForkDB
}