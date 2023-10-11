package com.vnstudio.cleanarchitecturedemo.presentation.list

import com.vnstudio.cleanarchitecturedemo.domain.entities.Fork

interface OnForkClickListener {

    fun onForkClick(fork: Fork)
}