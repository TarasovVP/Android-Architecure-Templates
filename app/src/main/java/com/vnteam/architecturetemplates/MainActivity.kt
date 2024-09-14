package com.vnteam.architecturetemplates

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class MainActivity : AppCompatActivity() {

    @BindView(R.id.progressBar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.listView)
    lateinit var listView: ListView
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ccom.vnteam.architecturetemplates.R

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.startButton)
    fun onStartButtonClick() {
        progressBar.isVisible = true
        val ormLiteSqliteDBConnector = OrmLiteSqliteDBConnector(this)
        val httpClientConnector = OkHttpClientConnector()
        httpClientConnector.makeHttpUrlConnection({ responseData ->
            val jsonConverter = JsonConverter()
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
                ormLiteSqliteDBConnector.insertDataAsync(jsonConverter.forkListToForkDBList(forks), {
                    val forkList = ormLiteSqliteDBConnector.getTransformedForks()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val sqLiteDBConnector = SQLiteDBConnector(this)
        val listView = findViewById<ListView>(R.id.listView)
        handler = Handler(Looper.getMainLooper()) { message ->
            when (message.what) {
                SUCCESS_HTTP_CONNECTION -> {
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
                    progressBar.isVisible = false
                    listView.setOnItemClickListener { _, _, position, _ ->
                        Log.e("apiTAG", "MainActivity SUCCESS_SQLITE_CONNECTION setOnItemClickListener position $position")
                        val intent = Intent(this, DetailsActivity::class.java)
                        intent.putExtra(FORK, forkList[position])
                        startActivity(intent)
                    }
                }, { errorText ->
                    Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                })
            }
        }, { errorText ->
            Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
        })
    }

    companion object {
        const val DATABASE_NAME = "AndroidArchitectureTemplates"
                    Log.e("apiTAG", "MainActivity SUCCESS_SQLITE_CONNECTION forksNameList.size ${forkList.size}")
                }
                ERROR -> {
                    progressBar.isVisible = false
                    val errorText = message.obj as String
                    Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        val startButton = findViewById<Button>(R.id.startButton)
        val httpUrlConnector = HttpUrlConnector()
        startButton.setOnClickListener {
            progressBar.isVisible = true
            httpUrlConnector.makeHttpUrlConnection(handler)
        }
    }

    companion object {
        const val SUCCESS_HTTP_CONNECTION = 1
        const val SUCCESS_SQLITE_CONNECTION = 2
        const val ERROR = 3
        const val SUCCESS_IMAGE_FROM_URL_CONNECTION = 4
        const val DATABASE_NAME = "CleanArchitectureDemo"
        const val TABLE_NAME = "forks"
        const val FORK = "fork"
    }
}