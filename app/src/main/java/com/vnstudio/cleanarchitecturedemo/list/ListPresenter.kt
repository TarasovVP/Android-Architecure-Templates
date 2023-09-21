package com.vnstudio.cleanarchitecturedemo.list

import com.vnstudio.cleanarchitecturedemo.JsonConverter
import com.vnstudio.cleanarchitecturedemo.OkHttpClientConnector

class ListPresenter {

    private var view: ListViewContract? = null

    fun attachView(view: ListViewContract) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun getForksFromApi() {
        view?.setProgressVisibility(true)
        val httpClientConnector = OkHttpClientConnector()
        httpClientConnector.makeHttpUrlConnection({ responseData ->
            val jsonConverter = JsonConverter()
            responseData?.let {
                val forks = jsonConverter.getForkList(responseData)
                view?.setForks(forks)
            }
        }, { errorText ->
            view?.showError(errorText)
        })
    }
}