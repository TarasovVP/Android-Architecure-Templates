package com.vnstudio.cleanarchitecturedemo.list

import com.vnstudio.cleanarchitecturedemo.models.Fork

interface ListViewContract {

    fun setProgressVisibility(showProgress: Boolean)

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB()

    fun setForksFromDB(forks: List<Fork>)

    fun showError(errorMessage: String)
}