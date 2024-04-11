package com.vnteam.architecturetemplates.list

import com.vnteam.architecturetemplates.models.Fork

interface ListViewContract {

    fun setProgressVisibility(showProgress: Boolean)

    fun insertForksToDB(forks: List<Fork>)

    fun getForksFromDB()

    fun setForksFromDB(forks: List<Fork>)

    fun showError(errorMessage: String)
}