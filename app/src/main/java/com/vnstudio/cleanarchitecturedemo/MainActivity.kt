package com.vnstudio.cleanarchitecturedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val sqLiteDBConnector = SQLiteDBConnector(this)
        val listView = findViewById<ListView>(R.id.listView)

        val startButton = findViewById<Button>(R.id.startButton)
        val httpUrlConnector = HttpUrlConnector()
        startButton.setOnClickListener {
            progressBar.isVisible = true
            httpUrlConnector.makeHttpUrlConnection({ responseData ->
                val jsonConverter = JsonConverter()
                responseData?.let {
                    val forks = jsonConverter.getForkList(responseData)
                    sqLiteDBConnector.insertDataAsync(forks, { forkList ->
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
    }

    companion object {
        const val DATABASE_NAME = "CleanArchitectureDemo"
        const val TABLE_NAME = "forks"
        const val FORK = "fork"
    }
}