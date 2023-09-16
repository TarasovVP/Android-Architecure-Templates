package com.vnstudio.cleanarchitecturedemo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper())

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            makeHttpRequest()
        }
    }

    private fun makeHttpRequest() {
        val thread = Thread {
            try {
                val url = "https://api.github.com/repos/octocat/Spoon-Knife/forks"
                val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode: Int = connection.responseCode
                if (responseCode == 200) {
                    val inputStream: InputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val stringBuilder = StringBuilder()
                    var line: String? = reader.readLine()
                    while (line != null) {
                        stringBuilder.append(line)
                        line = reader.readLine()
                    }
                    val responseData = stringBuilder.toString()
                    handler.post {
                        Log.e("apiTAG", "MainActivity responseData $responseData")
                        Toast.makeText(this, responseData, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    handler.post {
                        Toast.makeText(this, "Error responseCode $responseCode", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
        thread.start()
    }
}