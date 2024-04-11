package com.vnteam.architecturetemplates.database

import com.vnteam.architecturetemplates.models.Fork
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.Realm
import io.realm.RealmList

class RealmDBConnector(private val realm: Realm) {

    fun insertForksToDB(forks: List<Fork>): Observable<Unit> {
        return Observable.create<Unit> { emitter ->
            realm.executeTransactionAsync({ realmInstance ->
                val realmList = RealmList<Fork>()
                realmList.addAll(forks)
                realmInstance.insertOrUpdate(realmList)
                emitter.onNext(Unit)
                emitter.onComplete()
            }, { error ->
                emitter.onError(error)
                error.printStackTrace()
            })
        }.subscribeOn(AndroidSchedulers.mainThread())
    }

    fun getForksFromDB(): Observable<List<Fork>> {
        return Observable.create<List<Fork>> { emitter ->
            realm.runCatching {
                val forks = where(Fork::class.java)?.findAll().orEmpty()
                emitter.onNext(forks)
                emitter.onComplete()
            }.onFailure {error ->
                emitter.onError(error)
                error.printStackTrace()
            }
        }.subscribeOn(AndroidSchedulers.mainThread())
    }

    fun getForkById(forkId: Long?): Observable<Fork> {
        return Observable.create<Fork> { emitter ->
            realm.runCatching {
                val fork = where(Fork::class.java)?.equalTo("id", forkId)?.findFirst()
                fork.takeIf { it != null }?.let { emitter.onNext(it) } ?: emitter.onError(Throwable("Fork is not available"))
                emitter.onComplete()
            }.onFailure {error ->
                emitter.onError(error)
                error.printStackTrace()
            }
        }.subscribeOn(AndroidSchedulers.mainThread())
    }
}