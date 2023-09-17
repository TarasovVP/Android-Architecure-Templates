package com.vnstudio.cleanarchitecturedemo

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonConverter {

    fun getForkList(responseData: String): List<Fork> {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, Fork::class.java)
        val adapter: JsonAdapter<List<Fork>> = moshi.adapter(listType)
        return adapter.fromJson(responseData) ?: listOf()
    }
}