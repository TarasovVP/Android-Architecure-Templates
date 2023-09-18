package com.vnstudio.cleanarchitecturedemo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.AsyncTask
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.DATABASE_NAME

class OrmLiteSqliteDBConnector(context: Context) : OrmLiteSqliteOpenHelper(context, DATABASE_NAME, null, 1) {

    private var forkDao: Dao<ForkDB, Int>? = null

    override fun onCreate(database: SQLiteDatabase, connectionSource: ConnectionSource) {
        TableUtils.createTable(connectionSource, ForkDB::class.java)
    }
    override fun onUpgrade(database: SQLiteDatabase, connectionSource: ConnectionSource, oldVersion: Int, newVersion: Int) {
        TableUtils.dropTable<ForkDB, Int>(connectionSource, ForkDB::class.java, true)
        onCreate(database, connectionSource)
    }

    fun getForkDao(): Dao<ForkDB, Int>? {
        if (forkDao == null) {
            forkDao = DaoManager.createDao(connectionSource, ForkDB::class.java)
        }
        return forkDao
    }

    fun getTransformedForks(): List<Fork> {
        val forkList = arrayListOf<Fork>()
        forkDao?.queryForAll()?.forEach { forkDB ->
            forkList.add(Fork().apply {
                id = forkDB.id
                name = forkDB.name
                fullName = forkDB.fullName
                htmlUrl = forkDB.htmlUrl
                description = forkDB.description
                owner = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter(Owner::class.java).fromJson(forkDB.owner.orEmpty())
            })
        }
        return forkList
    }

    fun insertDataAsync(forks: List<ForkDB>, successResult: () -> Unit, errorResult: (String) -> Unit) {
        InsertDataTask(this, successResult, errorResult).execute(forks)
    }

    private class InsertDataTask(
        private val dbConnector: OrmLiteSqliteDBConnector,
        private val successResult: () -> Unit,
        private val errorResult: (String) -> Unit
    ) : AsyncTask<List<ForkDB>, Void, Pair<Unit, String>>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: List<ForkDB>): Pair<Unit, String> {
            var errorMessage = ""
            try {
                val forks = params[0]
                dbConnector.getForkDao()?.create(forks)
            } catch (e: Exception) {
                errorMessage = e.localizedMessage.toString()
            }
            return Pair(Unit, errorMessage)
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Pair<Unit, String>) {
            successResult.invoke()
            if (result.second.isNotEmpty()) errorResult.invoke(result.second)
        }
    }
}