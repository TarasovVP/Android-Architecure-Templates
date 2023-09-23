package com.vnstudio.cleanarchitecturedemo.details

import com.vnstudio.cleanarchitecturedemo.models.Fork

interface DetailsViewContract {

    fun setProgressVisibility(showProgress: Boolean)

    fun setForkFromDB(fork: Fork)

    fun showError(errorMessage: String)
}