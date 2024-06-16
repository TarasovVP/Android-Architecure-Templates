package com.vnteam.architecturetemplates.fork_service

import com.vnteam.architecturetemplates.ServerDatabaseQueries
import com.vnteam.architecturetemplates.domain.models.Fork
import com.vnteam.architecturetemplates.db.toFork

class ForkServiceImpl(private val serverDatabaseQueries: ServerDatabaseQueries):
    ForkService {

    override suspend fun insertForks(forks: List<Fork>) {
        forks.forEach {
            serverDatabaseQueries.insertForkWithOwner(
                forkId = it.forkId.orEmpty(),
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

    override suspend fun getForks(): List<Fork> {
        return serverDatabaseQueries.getForkWithOwners().executeAsList()
            .map { it.toFork() }
    }


    override suspend fun getForkById(forkId: String): Fork? =
        serverDatabaseQueries.getForkWithOwnerById(forkId).executeAsOneOrNull()?.toFork()

    override suspend fun deleteForkById(forkId: String) {
        serverDatabaseQueries.deleteForkWithOwnerById(forkId)
    }
}