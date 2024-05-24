package com.vnteam.architecturetemplates.data.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.vnteam.architecturetemplates.ForkWithOwner

class ForkDaoImpl(private val sharedDatabase: SharedDatabase): ForkDao {
    override suspend fun clearForks() {
        sharedDatabase { database ->
            database.appDatabaseQueries.clearForks()
        }
    }

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
            forkWithOwners(database.appDatabaseQueries.getForkWithOwners().awaitAsList())
        }
    }

    override suspend fun getForkById(id: Long, forkWithOwner: (ForkWithOwner?) -> Unit) {
        sharedDatabase { database ->
            forkWithOwner(database.appDatabaseQueries.getForkWithOwnerById(id).awaitAsOneOrNull())
        }
    }

    override suspend fun deleteForkById(id: Long, result: (Unit) -> Unit) {
        sharedDatabase { database ->
            database.appDatabaseQueries.deleteForkWithOwnerById(id)
            result(Unit)
        }
    }
}