package com.vnteam.architecturetemplates.data.database

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import com.vnteam.architecturetemplates.ForkWithOwner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

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
                        forkId = fork.forkId,
                        name = fork.name,
                        ownerId = fork.ownerId,
                        login = fork.login,
                        avatarUrl = fork.avatarUrl,
                        htmlUrl = fork.htmlUrl,
                        description = fork.description,
                        url = fork.url
                    )
                }
            }
        }
    }

    override suspend fun getForkWithOwners(): Flow<List<ForkWithOwner>> = callbackFlow {
        sharedDatabase { database ->
            trySend(database.appDatabaseQueries.getForkWithOwners().awaitAsList()).isSuccess
        }
        awaitClose { }
    }

    override suspend fun getForkById(id: String): Flow<ForkWithOwner?> = callbackFlow {
        sharedDatabase { database ->
            trySend(database.appDatabaseQueries.getForkWithOwnerById(id).awaitAsOneOrNull()).isSuccess
        }
        awaitClose { }
    }

    override suspend fun deleteForkById(id: String) {
        sharedDatabase { database ->
            database.appDatabaseQueries.deleteForkWithOwnerById(id)
        }
    }
}