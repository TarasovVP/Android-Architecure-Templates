package com.vnstudio.cleanarchitecturedemo

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class ValleyApiConnector(context: Context) {

    private var queue: RequestQueue? = null

    init {
        queue =
            Volley.newRequestQueue(context)
    }
    fun makeRequest(url: String, callback: (result: String?) -> Unit, errorCallBack: (String) -> Unit) {

        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                callback(response)
            }
        ) { error ->
            errorCallBack.invoke(error?.localizedMessage.toString())
            error.printStackTrace()
        }
        queue?.add(stringRequest)
    }
}