package com.vnteam.architecturetemplates

import com.vnteam.architecturetemplates.domain.models.Fork
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.getKoin

fun Routing.insertForksToDB() = post("/forks") {
    try {
        val forksPresenter = this.context.getKoin().get<ForksPresenter>()
        val forks = call.receive<List<Fork>>()
        forksPresenter.insertForksToDB(forks)
        call.respondText("Forks inserted successfully", status = HttpStatusCode.Created)
    } catch (e: Exception) {
        call.respondText("Error inserting forks: ${e.message}", status = HttpStatusCode.BadRequest)
    }
}

fun Routing.getForksFromDB() = get("/forks") {
    val forksPresenter = this.context.getKoin().get<ForksPresenter>()
    val forksList = forksPresenter.getForksFromDB()?.firstOrNull()
    if (forksList != null) {
        call.respond(forksList)
    } else {
        call.respondText("No forks found in the database", status = HttpStatusCode.NotFound)
    }
}

fun Routing.getForkById() = get("/forks/{id}") {
    val forkId = call.parameters["id"]?.toLongOrNull()
    if (forkId == null) {
        call.respond(HttpStatusCode.BadRequest, "Invalid fork ID")
        return@get
    }
    val forksPresenter = this.context.getKoin().get<ForksPresenter>()
    val fork = forksPresenter.getForkById(forkId)
    if (fork != null) {
        call.respond(fork)
    } else {
        call.respond(HttpStatusCode.NotFound, "Fork not found")
    }
    val forksList = forksJson().convertToDataClass<List<Fork>>()
    call.respond(HttpStatusCode.OK, forksList)
}

fun Routing.deleteForkById() = delete("/forks/{id}") {
    val forkId = call.parameters["id"]?.toLongOrNull()
    if (forkId == null) {
        call.respondText("Invalid fork ID", status = HttpStatusCode.BadRequest)
        return@delete
    }
    val forksPresenter = this.context.getKoin().get<ForksPresenter>()
    try {
        forksPresenter.deleteForkById(forkId)
        call.respondText("Fork deleted successfully", status = HttpStatusCode.OK)
    } catch (e: Exception) {
        call.respondText("Error deleting fork: ${e.message}", status = HttpStatusCode.InternalServerError)
    }
}