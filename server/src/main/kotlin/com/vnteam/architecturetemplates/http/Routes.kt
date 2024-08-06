package com.vnteam.architecturetemplates.http

import com.vnteam.architecturetemplates.domain.mappers.ForkResponseMapper
import com.vnteam.architecturetemplates.domain.responses.ForkResponse
import com.vnteam.architecturetemplates.fork_service.ForkService
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

fun Application.apiRoutes(forkService: ForkService, forkResponseMapper: ForkResponseMapper) {
    routing {
        insertForks(forkService, forkResponseMapper)
        getForks(forkService, forkResponseMapper)
        getForkById(forkService, forkResponseMapper)
        deleteForkById(forkService)
    }
}
fun Routing.insertForks(forkService: ForkService, forkResponseMapper: ForkResponseMapper) = post("/forks") {
    try {
        val forks = forkResponseMapper.mapFromImplModelList(call.receive<List<ForkResponse>>())
        forkService.insertForks(forks)
        call.respond(HttpStatusCode.Created)
    } catch (e: Exception) {
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForks(forkService: ForkService, forkResponseMapper: ForkResponseMapper) = get("/forks") {
    try {
        val forksList = forkResponseMapper.mapToImplModelList(forkService.getForks().orEmpty().toList())
        call.respond(forksList)
    } catch (e: Exception) {
        println("Routing.getForks Error: ${e.message}")
        call.respond(HttpStatusCode.BadRequest)
    }
}

fun Routing.getForkById(forkService: ForkService, forkResponseMapper: ForkResponseMapper) = get("/forks/{id}") {
    try {
        val forkId = call.parameters["id"]
        if (forkId == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }
        val fork = forkService.getForkById(forkId)?.let { it1 -> forkResponseMapper.mapToImplModel(it1) }
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