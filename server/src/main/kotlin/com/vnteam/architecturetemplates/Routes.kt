package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.apiRoutes(forkService: ForkService) {
    routing {
        insertForksToDB(forkService)
        getForksFromDB(forkService)
        getForkById(forkService)
        deleteForkById(forkService)
    }
}
fun Routing.insertForksToDB(forkService: ForkService) = post("/forks") {
    try {
        val forks = call.receive<List<Fork>>()
        forkService.insertForks(forks)
        call.respond(HttpStatusCode.Created)
    } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForksFromDB(forkService: ForkService) = get("/forks") {
    try {
        val forksList = forkService.getForks()?.toList()
        call.respond(forksList.orEmpty())
    } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForkById(forkService: ForkService) = get("/forks/{id}") {
    try {
        val forkId = call.parameters["id"]
        if (forkId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val fork = forkService.getForkById(forkId)
        if (fork != null) {
            call.respond(fork)
        } else {
            call.respond(HttpStatusCode.NotFound)
        }
    } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.deleteForkById(forkService: ForkService) = delete("/forks/{id}") {
    try {
        val forkId = call.parameters["id"]
        if (forkId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }
        try {
            forkService.deleteForkById(forkId)
            call.respond(HttpStatusCode.OK)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
        }
    } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest)
    }
}