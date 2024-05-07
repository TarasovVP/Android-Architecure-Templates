package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.ForkWithOwner

class ForkDaoImpl(private val sharedDatabase: SharedDatabase): ForkDao {

    override suspend fun insertForkWithOwners(forks: List<ForkWithOwner>) {
        sharedDatabase { database ->
            database.appDatabaseQueries.transaction {
                forks.forEach { fork ->
                    database.appDatabaseQueries.insertForkWithOwner(
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
    }

    override suspend fun getForks(forkWithOwners: (List<ForkWithOwner>) -> Unit) {
        sharedDatabase { database ->
            forkWithOwners(database.appDatabaseQueries.getForkWithOwners().executeAsList())
        }
    }

    override suspend fun getForkById(id: Long, forkWithOwner: (ForkWithOwner?) -> Unit) {
        sharedDatabase { database ->
            forkWithOwner(database.appDatabaseQueries.getForkWithOwnerById(id).executeAsOneOrNull())
        }
    }
}