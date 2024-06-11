package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.getKoin

fun Routing.insertForksToDB() = post("/forks") {
    println("insertForksToDB")
    try {
        val forksPresenter = this.context.getKoin().get<ForksPresenter>()
        val forks = call.receive<List<Fork>>()
        forksPresenter.insertForksToDB(forks)
        call.respond(HttpStatusCode.Created)
    } catch (e: Exception) {
        println("insertForksToDB ${e.message}")
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForksFromDB() = get("/forks") {
    println("getForksFromDB")
    try {
        val forksPresenter = this.context.getKoin().get<ForksPresenter>()
        val forksList = forksPresenter.getForksFromDB()?.toList()
        call.respond(forksList.orEmpty())
    } catch (e: Exception) {
        println("getForksFromDB ${e.message}")
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForkById() = get("/forks/{id}") {
    println("getForkById")
    try {
        val forkId = call.parameters["id"]
        if (forkId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val forksPresenter = this.context.getKoin().get<ForksPresenter>()
        val fork = forksPresenter.getForkById(forkId)
        if (fork != null) {
            call.respond(fork)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    } catch (e: Exception) {
        println("getForksFromDB ${e.message}")
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.deleteForkById() = delete("/forks/{id}") {
    println("deleteForkById")
    try {
        val forkId = call.parameters["id"]
        if (forkId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        val forksPresenter = this.context.getKoin().get<ForksPresenter>()
        try {
            forksPresenter.deleteForkById(forkId)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    } catch (e: Exception) {
        println("getForksFromDB ${e.message}")
        call.respond(HttpStatusCode.BadRequest)
    }
}