package com.example.routing.request

import kotlinx.serialization.Serializable

@Serializable
data class userRequest(
    val username: String,
    val password: String
)