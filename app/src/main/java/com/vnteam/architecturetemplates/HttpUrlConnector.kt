package com.vnstudio.cleanarchitecturedemo

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpUrlConnector {

    fun makeHttpUrlConnection(callback: (result: String?) -> Unit, errorCallBack: (String) -> Unit) {
        object : AsyncTask<Void, Void, String>() {
            @SuppressLint("StaticFieldLeak")
            override fun doInBackground(vararg params: Void): String? {
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
                        return stringBuilder.toString()
                    } else {
                        errorCallBack.invoke("Error responseCode $responseCode")
                        return null
                    }
                } catch (e: Exception) {
                    errorCallBack.invoke(e.localizedMessage)
                    return null
                }
            }

            override fun onPostExecute(result: String?) {
                callback(result)
            }
        }.execute()
    }

    fun getBitmapFromHttpUrlUrl(imageUrl: String?, callback: (result: Bitmap?) -> Unit, errorCallBack: (String) -> Unit) {
        object : AsyncTask<Void, Void, Bitmap>() {
            @SuppressLint("StaticFieldLeak")
            override fun doInBackground(vararg params: Void): Bitmap? {
                try {
                    val url = URL(imageUrl)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.doInput = true
                    connection.connect()

                    val inputStream: InputStream = connection.inputStream
                    return BitmapFactory.decodeStream(inputStream)
                } catch (e: Exception) {
                    errorCallBack.invoke(e.localizedMessage)
                    return null
                }
            }

            override fun onPostExecute(result: Bitmap?) {
                callback(result)
            }
        }.execute()
    }
}