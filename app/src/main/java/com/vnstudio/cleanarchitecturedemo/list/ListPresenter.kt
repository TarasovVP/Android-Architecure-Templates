package com.vnstudio.cleanarchitecturedemo.list

import android.annotation.SuppressLint
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORKS_URL
import com.vnstudio.cleanarchitecturedemo.database.ForkRepository
import com.vnstudio.cleanarchitecturedemo.models.Fork
import com.vnstudio.cleanarchitecturedemo.network.ValleyApiConnector
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ListPresenter @Inject constructor(
    private val valleyApiConnector: ValleyApiConnector,
    private val forkRepository: ForkRepository
){

    private var view: ListViewContract? = null

    fun attachView(view: ListViewContract) {
        this.view = view
    }

    @SuppressLint("CheckResult")
    fun getForksFromApi() {
        view?.setProgressVisibility(true)
        valleyApiConnector.makeRequest(FORKS_URL)
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
        forkRepository.insertForksToDB(forks)
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
        forkRepository.getForksFromDB()
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