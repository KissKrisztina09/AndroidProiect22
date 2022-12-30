package com.example.androidproiect22.ui.profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.User
import com.example.androidproiect22.service.UserService
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    companion object{
        val TAG: String = ProfileViewModel::class.java.simpleName
    }

    val profileRequestResult : MutableLiveData<ProfileResult> = MutableLiveData()
    private val userService = UserService
    lateinit var userData : User

    fun getProfile(){
        profileRequestResult.value = ProfileResult.LOADING

        val authToken = UserService.getAuthToken()
        if (authToken.isEmpty()) {
            profileRequestResult.value = ProfileResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val response = userService.getProfileDetails()
                if (response!= null) {
                    userData = response
                    profileRequestResult.value = ProfileResult.SUCCESS
                }
                else {
                    profileRequestResult.value = ProfileResult.INVALID_TOKEN
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message, ex)
                profileRequestResult.value = ProfileResult.UNKNOWN_ERROR
            }
        }
    }

    init{
        getProfile()
    }
}