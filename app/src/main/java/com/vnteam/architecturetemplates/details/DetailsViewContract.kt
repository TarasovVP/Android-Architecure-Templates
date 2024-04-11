package com.vnteam.architecturetemplates.details

import com.vnteam.architecturetemplates.models.Fork

interface DetailsViewContract {

    fun setProgressVisibility(showProgress: Boolean)

    fun setForkFromDB(fork: Fork)

    fun showError(errorMessage: String)
}