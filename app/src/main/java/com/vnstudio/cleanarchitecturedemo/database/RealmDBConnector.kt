package com.vnstudio.cleanarchitecturedemo.database

import com.vnstudio.cleanarchitecturedemo.models.Fork
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class RealmDBConnector(private val realm: Realm) {

    fun getForksFromDB(): Observable<List<Fork>> {
        return Observable.create<List<Fork>> { emitter ->
            try {
                realm.use { realmInstance ->
                    val forks = realmInstance.where(Fork::class.java)?.findAll().orEmpty()
                    emitter.onNext(forks)
                    emitter.onComplete()
                }
            } catch (error: Exception) {
                emitter.onError(error)
                error.printStackTrace()
            }
        }.subscribeOn(Schedulers.io())
    }

    fun insertForksToDB(forks: List<Fork>): Observable<Unit> {
        return Observable.create<Unit> { emitter ->
            realm.executeTransactionAsync({ realmInstance ->
                realmInstance.beginTransaction()
                val realmList = RealmList<Fork>()
                realmList.addAll(forks)
                realmInstance.insertOrUpdate(realmList)
                realmInstance.commitTransaction()
                emitter.onNext(Unit)
                emitter.onComplete()
            }, { error ->
                emitter.onError(error)
                error.printStackTrace()
            })
        }.subscribeOn(Schedulers.io())
    }
}