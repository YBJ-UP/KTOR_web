package com.example.routing

import com.example.model.user
import com.example.routing.request.userRequest
import com.example.routing.response.userResponse
import com.example.service.userService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import java.util.UUID

fun Route.userRoute(userService: userService){
    post {
        val userRequest = call.receive<userRequest>()

        val createdUser = userService.save(
            userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest)

        call.response.header(
            name= "id",
            value = createdUser.id.toString()
        )
        call.respond( HttpStatusCode.Created )
    }

    get {
        val users = userService.findAll()

        call.respond( users.map { user::toResponse } )
    }

    get("/{id}") {
        val id: String = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)

        val foundUser = userService.findByID(id) ?: return@get call.respond(HttpStatusCode.NotFound)

        call.respond( foundUser.toResponse() )
    }
}

private fun userRequest.toModel(): user =
    user(
        id = UUID.randomUUID(),
        username = this.username,
        password = this.password
    )

private fun user.toResponse(): userResponse =
    userResponse(
        this.id,
        this.username
    )