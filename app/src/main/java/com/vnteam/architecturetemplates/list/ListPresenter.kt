package com.vnstudio.cleanarchitecturedemo.list

import android.annotation.SuppressLint
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORKS_URL
import com.vnstudio.cleanarchitecturedemo.database.RealmDBConnector
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val valleyApiConnector: ValleyApiConnector,
    private val realmDBConnector: RealmDBConnector
){

    private var view: ListViewContract? = null

    fun attachView(view: ListViewContract) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun getForksFromApi() {
        view?.setProgressVisibility(true)
        valleyApiConnector.makeRequest(FORKS_URL).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { forks ->
                    realmDBConnector.insertForksToDB(forks).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {
                                view?.insertForksDB()
                                view?.setProgressVisibility(false)
                            },
                            { error ->
                                view?.showError(error?.localizedMessage.toString())
                                view?.setProgressVisibility(false)
                            }
                        )
                },
                { error ->
                    view?.showError(error?.localizedMessage.toString())
                    view?.setProgressVisibility(false)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun getForksFromDB() {
        view?.setProgressVisibility(true)
        realmDBConnector.getForksFromDB().observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { forks ->
                    view?.setForks(forks)
                    view?.setProgressVisibility(false)
                },
                { error ->
                    view?.showError(error?.localizedMessage.toString())
                    view?.setProgressVisibility(false)
                }
            )
    }

    fun detachView() {
        this.view = null
    }
}