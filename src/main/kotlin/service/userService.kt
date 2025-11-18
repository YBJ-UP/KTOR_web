package com.example.service

import com.example.model.user
import com.example.repository.userRepository
import java.util.UUID

class userService(private val userRepository: userRepository) {

    fun findAll(): List<user> = userRepository.findALL()

    fun findByID(id: String): user? = userRepository.findByID(id = UUID.fromString(id))

    fun findByUsername(username: String): user? = userRepository.findByUsername(username)

    fun save(user: user): user? {
        val foundUser = findByUsername(user.username)

        return if (foundUser == null) { userRepository.save(user); user } else { null }
    }
}