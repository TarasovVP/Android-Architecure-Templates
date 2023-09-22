package com.vnstudio.cleanarchitecturedemo

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vnstudio.cleanarchitecturedemo.models.Fork
import io.realm.RealmList


class ValleyApiConnector(context: Context) {

    private var queue: RequestQueue? = null

    init {
        queue =
            Volley.newRequestQueue(context)
    }
    fun makeRequest(url: String, result: (List<Fork>) -> Unit, errorCallBack: (String) -> Unit) {

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val listTypes = Types.newParameterizedType(List::class.java, Fork::class.java)
                val adapter: JsonAdapter<List<Fork>>? = moshi?.adapter(listTypes)
                val forks = adapter?.fromJson(response) ?: listOf()
                result.invoke(forks)
            }
        ) { error ->
            errorCallBack.invoke(error?.localizedMessage.toString())
            error.printStackTrace()
        }
        queue?.add(stringRequest)
    }
}