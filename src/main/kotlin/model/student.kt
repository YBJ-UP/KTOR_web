package com.example.model

import com.example.model.Teacher.autoIncrement
import com.example.model.Teacher.integer
import com.example.model.Teacher.references
import com.example.model.Teacher.varchar
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object Student: Table("students") {
    val id = integer("id").autoIncrement()
    val teacherId = integer("teacher_id").references(Teacher.id, onDelete = ReferenceOption.CASCADE)
    val nombre = varchar("nombre", 125)
    val apellidoP = varchar("apellidoPaterno", 125)
    val apellidoM = varchar("apellidoMaterno", 125)
    override val primaryKey = PrimaryKey(id)

}

@Serializable
data class StudentLogin(val nombre: String, val apellidoPaterno: String, val apellidoMaterno: String)

@Serializable
data class UserResponse(val id: Int, val fullName: String, val email: String? = null, val teacherId: Int? = null)

