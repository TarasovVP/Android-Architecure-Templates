package com.vnstudio.cleanarchitecturedemo.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vnstudio.cleanarchitecturedemo.models.Fork

class ValleyApiConnector(private val requestQueue: RequestQueue) {

    fun makeRequest(url: String, result: (List<Fork>) -> Unit, onError: (String) -> Unit)  {
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val listTypes = Types.newParameterizedType(List::class.java, Fork::class.java)
                val adapter: JsonAdapter<List<Fork>>? = moshi?.adapter(listTypes)
                val forks = adapter?.fromJson(response) ?: listOf()
                result.invoke(forks)
            }
        ) { error ->
            onError.invoke(error?.localizedMessage.toString())
            error.printStackTrace()
        }
        requestQueue.add(stringRequest)
    }
}