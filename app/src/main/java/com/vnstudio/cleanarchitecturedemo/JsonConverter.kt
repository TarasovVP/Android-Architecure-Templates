package com.vnstudio.cleanarchitecturedemo

import org.json.JSONArray
import org.json.JSONObject

class JsonConverter {

    fun getForkList(responseData: String): ArrayList<Fork> {
        val forkList = ArrayList<Fork>()
        val jsonArray = JSONArray(responseData)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.get(i) as JSONObject
            forkList.add(createForkObject(jsonObject))
        }
        return forkList
    }

    private fun createForkObject(jsonObject: JSONObject): Fork {
        val fork = Fork()
        fork.id = jsonObject.getLong("id")
        fork.name = jsonObject.getString("name")
        fork.full_name = jsonObject.getString("full_name")
        fork.html_url = jsonObject.getString("html_url")
        fork.description = jsonObject.getString("description")
        val ownerObject = jsonObject.getJSONObject("owner")
        val owner = Owner()
        owner.login = ownerObject.getString("login")
        owner.id = ownerObject.getLong("id")
        owner.avatar_url = ownerObject.getString("avatar_url")
        fork.owner = owner
        return fork
    }
}