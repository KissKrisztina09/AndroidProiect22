package com.example.androidproiect22.ui.create_task

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.Department
import com.example.androidproiect22.model.User
import com.example.androidproiect22.service.DepartmentService
import com.example.androidproiect22.service.TaskService
import com.example.androidproiect22.service.UserService
import com.example.androidproiect22.ui.tasks.TaskListViewModel
import kotlinx.coroutines.launch

class CreateTaskViewModel : ViewModel() {
    var createTaskResult : MutableLiveData<CreateTaskResult> = MutableLiveData()
    private val userService = UserService
    private val departmentService = DepartmentService
    private val taskService = TaskService
    lateinit var departments : List<Department>
    lateinit var users : List<User>

    private fun getData() {
        createTaskResult.value = CreateTaskResult.LOADING

        val authToken = UserService.getAuthToken()
        if (authToken.isEmpty()) {
            createTaskResult.value = CreateTaskResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val departmentsResponse = departmentService.getAllDepartments()
                val usersResponse = userService.getAllUsers()
                if (departmentsResponse != null && usersResponse != null) {
                    departments = departmentsResponse
                    users = usersResponse
                }
                createTaskResult.value = CreateTaskResult.SUCCESS
            }
            catch (ex: Exception) {
                Log.e(TaskListViewModel.TAG, ex.message, ex)
                createTaskResult.value = CreateTaskResult.UNKNOWN_ERROR
            }
        }
    }

    fun createTask(title: String, description: String, assigneeId : Int, priority : Int, deadline : Long, departmentId: Int) {
        val authToken = UserService.getAuthToken()
        if (authToken.isEmpty()) {
            createTaskResult.value = CreateTaskResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            val response = taskService.createTask(authToken, title, description, assigneeId, priority, deadline, departmentId)
            if (response) {
                createTaskResult.value = CreateTaskResult.UPLOAD_SUCCESS
            }
            else {
                createTaskResult.value = CreateTaskResult.UNKNOWN_ERROR
            }
        }
    }

    init {
        getData()
    }
}