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

        handler = Handler(Looper.getMainLooper()) { message ->
            when (message.what) {
                1 -> {
                    val responseData = message.obj as String
                    val jsonConverter = JsonConverter()
                    val fork = jsonConverter.getForkList(responseData)
                    Log.e("apiTAG", "MainActivity fork $fork")
                }
                2 -> {
                    val errorText = message.obj as String
                    Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

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
                    val message = handler.obtainMessage(1, responseData)
                    handler.sendMessage(message)
                } else {
                    val message = handler.obtainMessage(2, "Error responseCode $responseCode")
                    handler.sendMessage(message)
                }
            } catch (e: Exception) {
                val message = handler.obtainMessage(2, e.localizedMessage)
                handler.sendMessage(message)
            }
        }
        thread.start()
    }
}