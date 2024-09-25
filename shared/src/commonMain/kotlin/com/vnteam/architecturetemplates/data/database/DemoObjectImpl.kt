package com.vnteam.architecturetemplates.data.database

import com.vnteam.architecturetemplates.AppDatabaseQueries
import com.vnteam.architecturetemplates.DemoObjectWithOwner

class DemoObjectImpl(private val appDatabaseQueries: AppDatabaseQueries): DemoObject {

    override fun insertDemoObjectWithOwners(demoObject: List<DemoObjectWithOwner>) {
        appDatabaseQueries.transaction {
            demoObject.forEach { demoObject ->
                appDatabaseQueries.insertDemoObjectWithOwner(
                    id = demoObject.id,
                    name = demoObject.name,
                    fullName = demoObject.fullName,
                    ownerId = demoObject.ownerId,
                    login = demoObject.login,
                    avatarUrl = demoObject.avatarUrl,
                    htmlUrl = demoObject.htmlUrl,
                    description = demoObject.description
                )
            }
        }
    }

    override fun getDemoObjects(): List<DemoObjectWithOwner> {
        return appDatabaseQueries.getDemoObjectWithOwners().executeAsList()
    }

    override fun getDemoObjectById(id: Long): DemoObjectWithOwner? {
        return appDatabaseQueries.getDemoObjectWithOwnerById(id).executeAsOneOrNull()
    }
}