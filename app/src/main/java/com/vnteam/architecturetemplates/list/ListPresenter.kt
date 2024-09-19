package com.vnteam.architecturetemplates.list

import android.annotation.SuppressLint
import com.vnstudio.cleanarchitecturedemo.presentation.MainActivity.Companion.FORKS_URL
import com.vnteam.architecturetemplates.database.RealmDBConnector
import com.vnteam.architecturetemplates.models.Fork
import com.vnteam.architecturetemplates.network.ValleyApiConnector
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
                    view?.insertForksToDB(forks)
                    view?.setProgressVisibility(false)
                },
                { error ->
                    view?.showError(error?.localizedMessage.toString())
                    view?.setProgressVisibility(false)
                }
            )
    }

    @SuppressLint("CheckResult")
    fun insertForksToDB(forks: List<Fork>) {
        view?.setProgressVisibility(true)
        realmDBConnector.insertForksToDB(forks).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view?.getForksFromDB()
                    view?.setProgressVisibility(false)
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
                    view?.setForksFromDB(forks)
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