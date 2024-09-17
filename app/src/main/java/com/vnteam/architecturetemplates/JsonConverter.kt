package com.vnteam.architecturetemplates

import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class JsonConverter {

    private var moshi: Moshi? = null

    init {
        moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }
    fun getForkList(responseData: String): List<Fork> {
        val listType = Types.newParameterizedType(List::class.java, Fork::class.java)
        val adapter: JsonAdapter<List<Fork>>? = moshi?.adapter(listType)
        return adapter?.fromJson(responseData) ?: listOf()
    }

    fun forkListToForkDBList(forks: List<Fork>): List<ForkDB> {
        val forkDBList = mutableListOf<ForkDB>()
        forks.forEach { fork ->
            forkDBList.add(ForkDB().apply {
                id = fork.id
                name = fork.name
                fullName = fork.fullName
                htmlUrl = fork.htmlUrl
                description = fork.description
                owner = moshi?.adapter(Owner::class.java)?.toJson(fork.owner)
            })
        }
        return forkDBList
    }
}