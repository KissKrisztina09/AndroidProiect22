package com.example.androidproiect22.service

import com.example.androidproiect22.api.ApplicationRemoteSource
import com.example.androidproiect22.api.mappers.DtoMappers
import com.example.androidproiect22.model.Department

object DepartmentService {
    suspend fun getDepartmentById(id: Int) : Department? {
        val authToken: String = UserService.getAuthToken()
        val response = ApplicationRemoteSource.getApi()?.getDepartments(authToken)

        if (response?.isSuccessful == true) {
            val departments : List<Department> = response.body()!!.map {DtoMappers.departmentFromDto(it)}
            return departments.find{it.id == id}
        }
        return null
    }

    suspend fun getAllDepartments() : List<Department>? {
        val authToken: String = UserService.getAuthToken()
        val response = ApplicationRemoteSource.getApi()?.getDepartments(authToken)
        if (response?.isSuccessful == true) {
            return response.body()!!.map { DtoMappers.departmentFromDto(it) }
        }
        return null
    }

}