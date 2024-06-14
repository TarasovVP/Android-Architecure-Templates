package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork

class ForkServiceImpl(private val serverDatabase: ServerDatabase):
    ForkService {

    override suspend fun insertForks(forks: List<Fork>) {
        forks.forEach {
            serverDatabase.appDatabaseQueries.insertForkWithOwner(
                forkId = it.forkId,
                name = it.name,
                htmlUrl = it.htmlUrl,
                description = it.description,
                login = it.owner?.login,
                ownerId = it.owner?.ownerId,
                avatarUrl = it.owner?.avatarUrl,
                url = it.owner?.url
            )
        }
    }

    override suspend fun getForks(): List<Fork> =
        serverDatabase.appDatabaseQueries.getForkWithOwners().executeAsList()
            .map { it.toFork() }


    override suspend fun getForkById(forkId: String): Fork? =
        serverDatabase.appDatabaseQueries.getForkWithOwnerById(forkId).executeAsOneOrNull()?.toFork()

    override suspend fun deleteForkById(forkId: String) {
        serverDatabase.appDatabaseQueries.deleteForkWithOwnerById(forkId)
    }
}