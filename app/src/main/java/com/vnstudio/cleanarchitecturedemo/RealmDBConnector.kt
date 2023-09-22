package com.vnstudio.cleanarchitecturedemo

import com.vnstudio.cleanarchitecturedemo.models.Fork
import io.realm.Realm
import io.realm.RealmList

class RealmDBConnector {

    private var realm: Realm? = null

    init {
        realm = Realm.getDefaultInstance()
    }

    fun getForksFromDB(): List<Fork> {
        return realm?.where(Fork::class.java)?.findAll().orEmpty()
    }

    fun insertForksToDB(forks: List<Fork>) {
        realm?.beginTransaction()
        val realmList = RealmList<Fork>()
        realmList.addAll(forks)
        realm?.insertOrUpdate(realmList)
        realm?.commitTransaction()
    }
}