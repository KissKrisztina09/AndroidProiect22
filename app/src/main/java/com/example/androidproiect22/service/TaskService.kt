package com.example.androidproiect22.service

import android.annotation.SuppressLint
import com.example.androidproiect22.api.ApplicationRemoteSource
import com.example.androidproiect22.api.createTask.CreateTaskRequest
import com.example.androidproiect22.api.mappers.DtoMappers
import com.example.androidproiect22.model.Task

object TaskService {
    @SuppressLint("SuspiciousIndentation")
    suspend fun getTasks(authToken: String) : List<Task>? {
      val response = ApplicationRemoteSource.getApi()?.getAllTasks( authToken = authToken)
        if (response?.isSuccessful == true) {
            return response.body()!!.map {DtoMappers.taskFromDto(it)}
        }
        return null
    }

    suspend fun createTask(authToken: String, title: String, description: String, assigneeId : Int, priority : Int, deadline : Long, departmentId: Int) : Boolean {
        val createTaskRequest = CreateTaskRequest(title, description, assigneeId, priority, deadline, departmentId, 0)
        val response = ApplicationRemoteSource.getApi()?.createTask(createTaskRequest = createTaskRequest, authToken = authToken)
        if (response?.isSuccessful == true) {
            if (response.body()!!.message == "Success") {
                return true
            }
            return false
        }
        return false
    }

}