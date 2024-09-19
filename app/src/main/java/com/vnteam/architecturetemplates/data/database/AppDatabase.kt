<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/data/database/AppDatabase.kt
package com.vnteam.architecturetemplates.data.database
========
package com.vnteam.architecturetemplates.database
>>>>>>>> master:app/src/main/java/com/vnteam/architecturetemplates/database/AppDatabase.kt

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
<<<<<<<< HEAD:app/src/main/java/com/vnteam/architecturetemplates/data/database/AppDatabase.kt
import com.vnteam.architecturetemplates.data.database.entities.ForkDB
========
import com.vnteam.architecturetemplates.models.Fork
>>>>>>>> master:app/src/main/java/com/vnteam/architecturetemplates/database/AppDatabase.kt

@Database(
    entities = [ForkDB::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun forkDao(): ForkDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, appContext.packageName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }
}