package com.vnstudio.cleanarchitecturedemo.list

import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORKS_URL
import com.vnstudio.cleanarchitecturedemo.RealmDBConnector
import com.vnstudio.cleanarchitecturedemo.ValleyApiConnector
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val valleyApiConnector: ValleyApiConnector,
    private val realmDBConnector: RealmDBConnector
){

    private var view: ListViewContract? = null

    fun attachView(view: ListViewContract) {
        this.view = view
    }

    fun getForksFromApi() {
        view?.setProgressVisibility(true)
        valleyApiConnector.makeRequest(FORKS_URL, { forks ->
            realmDBConnector.insertForksToDB(forks)
            view?.insertForksDB()
            view?.setProgressVisibility(false)
        }, { errorText ->
            view?.showError(errorText)
            view?.setProgressVisibility(false)
        })
    }

    fun getForksFromDB() {
        view?.setProgressVisibility(true)
        val forks = realmDBConnector.getForksFromDB()
        view?.setForks(forks)
        view?.setProgressVisibility(false)
    }

    fun detachView() {
        this.view = null
    }
}