package com.vnstudio.cleanarchitecturedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.AsyncTask
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.DATABASE_NAME
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.TABLE_NAME

class SQLiteDBConnector(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (id LONG,name VARCHAR(256),full_name VARCHAR(256), owner_login VARCHAR(256), owner_id LONG, owner_avatar_url VARCHAR(256), html_url VARCHAR(256), description VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertDataAsync(forks: ArrayList<Fork>, successResult: (ArrayList<Fork>) -> Unit, errorResult: (String) -> Unit) {
        InsertDataTask(this, successResult, errorResult).execute(forks)
    }

    private class InsertDataTask(
        private val dbConnector: SQLiteDBConnector,
        private val successResult: (ArrayList<Fork>) -> Unit,
        private val errorResult: (String) -> Unit
    ) : AsyncTask<ArrayList<Fork>, Void, Pair<ArrayList<Fork>, String>>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: ArrayList<Fork>): Pair<ArrayList<Fork>, String> {
            val forks = params[0]
            val errorMessages = mutableListOf<String>()

            for (fork in forks) {
                val contentValues = ContentValues()
                contentValues.put("id", fork.id ?: 0)
                contentValues.put("name", fork.name)
                contentValues.put("full_name", fork.full_name)
                contentValues.put("owner_login", fork.owner?.login)
                contentValues.put("owner_id", fork.owner?.id ?: 0)
                contentValues.put("owner_avatar_url", fork.owner?.avatar_url)
                contentValues.put("html_url", fork.html_url)
                contentValues.put("description", fork.description)

                val database = dbConnector.writableDatabase
                val result = database.insert(TABLE_NAME, null, contentValues)
                if (result == (0).toLong()) {
                    errorMessages.add("Error inserting for fork with id ${fork.id}")
                }
            }
            return Pair(forks, errorMessages.joinToString())
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Pair<ArrayList<Fork>, String>) {
            successResult.invoke(result.first)
            if (result.second.isNotEmpty()) errorResult.invoke(result.second)
        }
    }

    fun readData(): MutableList<Fork> {
        val forks: MutableList<Fork> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val fork = Fork()
                fork.id = result.getLong(result.getColumnIndex("id") ?: 0)
                fork.name = result.getString(result.getColumnIndex("name") ?: 0)
                fork.full_name = result.getString(result.getColumnIndex("full_name") ?: 0)
                fork.owner?.login = result.getString(result.getColumnIndex("owner_login") ?: 0)
                fork.owner?.id = result.getLong(result.getColumnIndex("owner_id") ?: 0)
                fork.owner?.avatar_url = result.getString(result.getColumnIndex("owner_avatar_url") ?: 0)
                fork.html_url = result.getString(result.getColumnIndex("html_url") ?: 0)
                fork.description = result.getString(result.getColumnIndex("description") ?: 0)
                forks.add(fork)
            }
            while (result.moveToNext())
        }
        return forks
    }
}