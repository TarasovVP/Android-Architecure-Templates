package com.vnstudio.cleanarchitecturedemo.details

import android.annotation.SuppressLint
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val forkRepository: ForkRepository
){

    private var view: DetailsViewContract? = null

    fun attachView(view: DetailsViewContract) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun getForkById(forkId: Long?) {
        view?.setProgressVisibility(true)
        forkRepository.getForkById(forkId)
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