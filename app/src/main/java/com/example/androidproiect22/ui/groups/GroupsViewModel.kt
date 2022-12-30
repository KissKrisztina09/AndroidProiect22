package com.example.androidproiect22.ui.groups

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.Department
import com.example.androidproiect22.service.DepartmentService
import com.example.androidproiect22.service.UserService
import com.example.androidproiect22.ui.tasks.TaskListViewModel
import kotlinx.coroutines.launch

class GroupsViewModel : ViewModel() {
    val departmentResult: MutableLiveData<GroupResult> = MutableLiveData()

    private val departmentService = DepartmentService

    lateinit var departmentList : List<Department>

    fun getDepartments() {
        departmentResult.value = GroupResult.LOADING

        val authToken = UserService.getAuthToken()

        if (authToken.isEmpty()) {
            departmentResult.value = GroupResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val response = departmentService.getAllDepartments()
                if (response!= null) {
                    departmentList = response
                    departmentResult.value = GroupResult.SUCCESS
                }
            }
            catch (ex:Exception) {
                Log.e(TaskListViewModel.TAG, ex.message, ex)
                departmentResult.value = GroupResult.UNKNOWN_ERROR
            }
        }
    }

    init{
        getDepartments()
    }
}