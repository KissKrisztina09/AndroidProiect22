package com.example.androidproiect22.api.mappers

import com.example.androidproiect22.api.department.DepartmentResponse
import com.example.androidproiect22.api.user.UserResponse
import com.example.androidproiect22.api.task.TaskResponse
import com.example.androidproiect22.model.Department
import com.example.androidproiect22.model.User
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.service.DepartmentService
import com.example.androidproiect22.service.UserService

object DtoMappers {
    fun userFromDto(dto : UserResponse) : User {
        if (dto.location == null) {
            dto.location = "none"
        }
        if (dto.phoneNumber == null) {
            dto.phoneNumber = "none"
        }
        return User(dto.userId, dto.lastName, dto.firstName, dto.email,
            dto.location!!, dto.phoneNumber!!, dto.departmentId, dto.imageUrl)
    }

    suspend fun taskFromDto(dto: TaskResponse) : Task {
        val assignee : User? = UserService.getUserById(dto.assigneeId)
        val creator : User? = UserService.getUserById(dto.creatorId)
        val department : Department? = DepartmentService.getDepartmentById(dto.departmentId)
        return Task(dto.taskId, dto.title, dto.description, dto.createdTime, dto.creatorId, creator, dto.assigneeId, assignee, dto.priority, dto.deadline, dto.departmentId, department)
    }

    suspend fun departmentFromDto(dto: DepartmentResponse) : Department {
        return Department(dto.id, dto.name)
    }
}