package com.vnstudio.cleanarchitecturedemo.presentation.list

import com.vnstudio.cleanarchitecturedemo.domain.models.Fork

interface OnForkClickListener {

    fun onForkClick(fork: Fork)
}