package com.vnteam.cleanarchitecturedemo.data.database

import com.vnteam.cleanarchitecturedemo.ForkWithOwner

interface ForkDao {

    fun insertForkWithOwners(forks: List<ForkWithOwner>)

    fun getForks(): List<ForkWithOwner>

    fun getForkById(id: Long): ForkWithOwner?
}