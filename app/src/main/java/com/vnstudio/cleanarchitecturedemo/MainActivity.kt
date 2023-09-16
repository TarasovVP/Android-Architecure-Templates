package com.vnstudio.cleanarchitecturedemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sqLiteDBConnector = SQLiteDBConnector(this)
        val listView = findViewById<ListView>(R.id.listView)
        handler = Handler(Looper.getMainLooper()) { message ->
            when (message.what) {
                SUCCESS_HTTPS_CONNECTION -> {
                    val responseData = message.obj as String
                    val jsonConverter = JsonConverter()
                    val forks = jsonConverter.getForkList(responseData)

                    sqLiteDBConnector.insertData(handler, forks)
                    Log.e("apiTAG", "MainActivity SUCCESS_HTTPS_CONNECTION fork $forks")
                }
                SUCCESS_SQLITE_CONNECTION -> {
                    val forkList = message.obj as ArrayList<Fork>
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, forkList.map { it.name })
                    listView.adapter = adapter
                    listView.setOnItemClickListener { _, _, position, _ ->
                        Log.e("apiTAG", "MainActivity SUCCESS_SQLITE_CONNECTION setOnItemClickListener position $position")
                        val intent = Intent(this, DetailsActivity::class.java)
                        intent.putExtra(FORK, forkList[position])
                        startActivity(intent)
                    }
                    Log.e("apiTAG", "MainActivity SUCCESS_SQLITE_CONNECTION forksNameList.size ${forkList.size}")
                }
                ERROR -> {
                    val errorText = message.obj as String
                    Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        val startButton = findViewById<Button>(R.id.startButton)
        val httpUrlConnector = HttpUrlConnector()
        startButton.setOnClickListener {
            httpUrlConnector.makeHttpUrlConnection(handler)
        }
    }

    companion object {
        const val SUCCESS_HTTPS_CONNECTION = 1
        const val SUCCESS_SQLITE_CONNECTION = 2
        const val ERROR = 3
        const val DATABASE_NAME = "CleanArchitectureDemo"
        const val TABLE_NAME = "forks"
        const val FORK = "fork"
    }
}