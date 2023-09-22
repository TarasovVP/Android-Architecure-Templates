package com.vnstudio.cleanarchitecturedemo.list

import com.vnstudio.cleanarchitecturedemo.JsonConverter
import com.vnstudio.cleanarchitecturedemo.MainActivity.Companion.FORKS_URL
import com.vnstudio.cleanarchitecturedemo.ValleyApiConnector
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val valleyApiConnector: ValleyApiConnector,
    private val jsonConverter: JsonConverter
){

    private var view: ListViewContract? = null

    fun attachView(view: ListViewContract) {
        this.view = view
    }

    fun getForksFromApi() {
        view?.setProgressVisibility(true)
        valleyApiConnector.makeRequest(FORKS_URL, { responseData ->
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
                view?.setForks(forks)
            }
        }, { errorText ->
            view?.showError(errorText)
        })
    }

    fun getForksFromDB() {
        view?.setProgressVisibility(true)
        valleyApiConnector.makeRequest(FORKS_URL, { responseData ->
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
                view?.setForks(forks)
            }
        }, { errorText ->
            view?.showError(errorText)
        })
    }

    fun detachView() {
        this.view = null
    }
}