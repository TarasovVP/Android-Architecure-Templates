package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.ForkWithOwner

interface ForkDao {

    fun insertForkWithOwners(forks: List<ForkWithOwner>)

    fun getForks(): List<ForkWithOwner>

    fun getForkById(id: Long): ForkWithOwner?
}