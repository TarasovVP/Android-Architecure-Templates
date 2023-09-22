package com.vnstudio.cleanarchitecturedemo.list

import com.vnstudio.cleanarchitecturedemo.models.Fork

interface ListViewContract {

    fun setProgressVisibility(showProgress: Boolean)

    fun insertForksDB()

    fun setForks(forks: List<Fork>)

    fun showError(errorMessage: String)
}