package com.vnteam.architecturetemplates

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Handler
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.DATABASE_NAME
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.TABLE_NAME

class SQLiteDBConnector(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (id LONG,name VARCHAR(256),full_name VARCHAR(256), owner_login VARCHAR(256), owner_id LONG, owner_avatar_url VARCHAR(256), html_url VARCHAR(256), description VARCHAR(256))"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertData(handler: Handler, forks: ArrayList<Fork>) {
        val database = this.writableDatabase
        val successMessages = mutableListOf<String>()
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

            val result = database.insert(TABLE_NAME, null, contentValues)
            if (result == (0).toLong()) {
                errorMessages.add("Error inserting for fork with id ${fork.id}")
            } else {
                successMessages.add("Success inserting for fork with id ${fork.id}")
            }
        }

        if (errorMessages.isNotEmpty()) {
            val errorMessage = handler.obtainMessage(MainActivity.ERROR, errorMessages.joinToString("\n"))
            handler.sendMessage(errorMessage)
        }
        if (successMessages.isNotEmpty()) {
            val forkList = handler.obtainMessage(MainActivity.SUCCESS_SQLITE_CONNECTION, forks)
            handler.sendMessage(forkList)
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