package com.vnstudio.cleanarchitecturedemo.network

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vnstudio.cleanarchitecturedemo.models.Fork
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class ValleyApiConnector(private val requestQueue: RequestQueue) {

    fun makeRequest(url: String): Observable<List<Fork>> {
        return Observable.create { emitter ->
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                    val listTypes = Types.newParameterizedType(List::class.java, Fork::class.java)
                    val adapter: JsonAdapter<List<Fork>>? = moshi?.adapter(listTypes)
                    val forks = adapter?.fromJson(response) ?: listOf()
                    emitter.onNext(forks)
                    emitter.onComplete()
                }
            ) { error ->
                emitter.onError(error)
                error.printStackTrace()
            }
            requestQueue.add(stringRequest)
        }.subscribeOn(Schedulers.io())
    }
}