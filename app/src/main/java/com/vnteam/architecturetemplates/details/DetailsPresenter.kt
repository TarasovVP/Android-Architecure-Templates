package com.vnteam.architecturetemplates.details

import android.annotation.SuppressLint
import com.vnteam.architecturetemplates.database.RealmDBConnector
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val realmDBConnector: RealmDBConnector
){

    private var view: DetailsViewContract? = null

    fun attachView(view: DetailsViewContract) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun getForkById(forkId: Long?) {
        view?.setProgressVisibility(true)
        realmDBConnector.getForkById(forkId).observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { fork ->
                    view?.setForkFromDB(fork)
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