package com.example.androidproiect22.ui.tasks

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.service.TaskService
import com.example.androidproiect22.service.UserService
import com.example.androidproiect22.ui.settings.SettingsViewModel
import com.example.androidproiect22.ui.task_details.TaskDetailViewModel
import kotlinx.coroutines.launch

class TaskListViewModel : ViewModel() {
    companion object{
        val TAG: String = TaskListViewModel::class.java.simpleName
    }

    val taskResult : MutableLiveData<TaskResult> = MutableLiveData()

    private val taskService = TaskService

    lateinit var taskList : List<Task>

    fun getTasks(){
        taskResult.value = TaskResult.LOADING

        val authToken = UserService.getAuthToken()

        if (authToken.isEmpty()) {
            taskResult.value = TaskResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val response = taskService.getTasks(authToken)
                if (response != null) {
                    taskList = response
                    taskResult.value = TaskResult.SUCCESS
                }

            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message, ex)
                taskResult.value = TaskResult.UNKNOWN_ERROR
            }

        }
    }


    init {
        getTasks()
    }
}