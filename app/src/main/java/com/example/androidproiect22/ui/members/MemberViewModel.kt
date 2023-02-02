package com.example.androidproiect22.ui.members

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.Department
import com.example.androidproiect22.model.Task
import com.example.androidproiect22.model.User
import com.example.androidproiect22.service.DepartmentService
import com.example.androidproiect22.service.TaskService
import com.example.androidproiect22.service.UserService
import com.example.androidproiect22.ui.groups.GroupResult
import com.example.androidproiect22.ui.tasks.TaskListViewModel
import com.example.androidproiect22.ui.tasks.TaskListViewModel.Companion.TAG
import com.example.androidproiect22.ui.tasks.TaskResult
import kotlinx.coroutines.launch

class MemberViewModel : ViewModel() {

    val memberResult: MutableLiveData<MemberResult> = MutableLiveData()

    private val userService = UserService

    lateinit var userList : List<User>

    fun getMembers() {
        memberResult.value = MemberResult.LOADING

        val authToken = UserService.getAuthToken()

        if (authToken.isEmpty()) {
            memberResult.value = MemberResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val response = userService.getAllUsers()
                if (response!= null) {
                    userList = response
                    memberResult.value = MemberResult.SUCCESS
                }
            }
            catch (ex:Exception) {
                Log.e(TaskListViewModel.TAG, ex.message, ex)
                memberResult.value = MemberResult.UNKNOWN_ERROR
            }
        }
    }

    init{
        getMembers()
    }
}
