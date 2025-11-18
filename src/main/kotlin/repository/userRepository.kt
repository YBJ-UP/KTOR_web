package com.example.repository

import com.example.model.user
import java.util.UUID

class userRepository {

    private val users = mutableListOf<user>()

    fun findALL(): List<user> = users

    fun findByID(id: UUID): user? = users.firstOrNull { it.id == id }

    fun findByUsername(username: String): user? = users.firstOrNull { it.username == username }

    fun save(user: user): Boolean = users.add(user)
}