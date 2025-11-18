package com.example

import com.example.plugins.configureSerialization
import com.example.repository.userRepository
import com.example.routing.configureRouting
import com.example.service.userService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = userRepository()
    val userService = userService(userRepository)
    configureSerialization()
    configureRouting(userService)
}
