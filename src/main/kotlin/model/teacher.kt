package com.example.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Teacher: Table("teacher") {
    val id = integer("id").autoIncrement()
    val nombre = varchar("first_name", 125)
    val apellidos = varchar("last_name", 125)
    val email = varchar("email", 128).uniqueIndex()
    val contraseña =varchar("password", 128)
    val escuela= varchar("escuela", 125).nullable()
    override val primaryKey = PrimaryKey(id)

}

@Serializable
data class TeacherRegister(val nombre: String, val apellidos: String, val email: String, val contraseña: String, val escuela: String)

@Serializable
data class TeacherLogin(val email: String, val contraseña: String)