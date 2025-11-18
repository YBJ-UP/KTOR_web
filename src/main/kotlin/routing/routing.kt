package com.example.routing

import com.example.service.userService
import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting(userService: userService) {
    routing {
        route("/api/users") {
            userRoute(userService)
        }
    }
}