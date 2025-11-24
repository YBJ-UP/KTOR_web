package com.example.repository

import com.example.model.Student
import com.example.model.Student.teacherId
import com.example.model.StudentCreate
import com.example.model.Teacher
import com.example.model.TeacherRegister
import com.example.model.UserResponse
import com.example.model.user
import com.example.routing.response.userResponse
import com.sun.tools.javac.tree.TreeInfo.fullName
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepository {

    fun createTeacher(user: TeacherRegister): Int{
        return transaction {
            Teacher.insert {
                it[nombre] = user.nombre
                it[apellidos] = user.apellidos
                it[email] = user.email
                it[contraseña] = user.contraseña
                it[escuela] = user.escuela
            }get Teacher.id
        }
    }

    fun createStudent(tId: Int, student: StudentCreate){
        transaction {
            Student.insert {
                it[teacherId] = tId
                it[nombre] = student.nombre
                it[apellidoP] = student.apellidoP
                it[apellidoM] = student.apellidoM
            }
        }
    }

    fun FinteacherByEmail(email: String): ResultRow?{
        return transaction {
            Teacher.select { Teacher.email eq email }.singleOrNull()
        }
    }

    fun getStudentByTeacher(tId: Int): List<UserResponse> {
        return transaction{
            Student.select { Student.teacherId eq tId }
                .map{
                    UserResponse(
                    id = it[Student.id],
                    fullName = "${it[Student.nombre]} ${it[Student.apellidoP]} ${it[Student.apellidoM]}",
                    teacherId = it[Student.teacherId]

                )
            }
        }
    }








}

