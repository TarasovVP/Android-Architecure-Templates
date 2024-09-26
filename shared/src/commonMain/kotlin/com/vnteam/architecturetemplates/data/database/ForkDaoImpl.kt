package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.AppDatabaseQueries
import com.vnteam.architecturetemplates.ForkWithOwner

class ForkDaoImpl(private val appDatabaseQueries: AppDatabaseQueries): ForkDao {

    override fun insertForkWithOwners(forks: List<ForkWithOwner>) {
        appDatabaseQueries.transaction {
            forks.forEach { fork ->
                appDatabaseQueries.insertForkWithOwner(
                    id = fork.id,
                    name = fork.name,
                    fullName = fork.fullName,
                    ownerId = fork.ownerId,
                    login = fork.login,
                    avatarUrl = fork.avatarUrl,
                    htmlUrl = fork.htmlUrl,
                    description = fork.description
                )
            }
        }
    }

    override fun getForks(): List<ForkWithOwner> {
        return appDatabaseQueries.getForkWithOwners().executeAsList()
    }

    override fun getForkById(id: Long): ForkWithOwner? {
        return appDatabaseQueries.getForkWithOwnerById(id).executeAsOneOrNull()
    }
}