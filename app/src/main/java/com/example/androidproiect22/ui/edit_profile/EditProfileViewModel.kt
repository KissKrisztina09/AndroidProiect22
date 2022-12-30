package com.example.androidproiect22.ui.edit_profile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproiect22.model.User
import com.example.androidproiect22.service.UserService
import com.example.androidproiect22.ui.profile.ProfileViewModel
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {
    var editProfileResult : MutableLiveData<EditProfileResult> = MutableLiveData()
    private val userService = UserService

    lateinit var userData : User

    private fun getProfile(){
        editProfileResult.value = EditProfileResult.LOADING

        val authToken = UserService.getAuthToken()
        if (authToken.isEmpty()) {
            editProfileResult.value = EditProfileResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            try {
                val response = userService.getProfileDetails()
                if (response!= null) {
                    userData = response
                    editProfileResult.value = EditProfileResult.SUCCESS
                }
                else {
                    editProfileResult.value = EditProfileResult.INVALID_TOKEN
                }
            }
            catch (ex: Exception) {
                Log.e(ProfileViewModel.TAG, ex.message, ex)
                editProfileResult.value = EditProfileResult.UNKNOWN_ERROR
            }
        }
    }

    fun updateProfile(lastName: String, firstName : String, location: String, phoneNumber: String, imageUrl : String) {
        val authToken = UserService.getAuthToken()
        if (authToken.isEmpty()) {
            editProfileResult.value = EditProfileResult.INVALID_TOKEN
            return
        }

        viewModelScope.launch {
            val response = userService.updateProfile(authToken, lastName, firstName, location, phoneNumber, imageUrl)
            if (response) {
                editProfileResult.value = EditProfileResult.EDIT_SUCCESS
            }
            else {
                editProfileResult.value = EditProfileResult.UNKNOWN_ERROR
            }
        }
    }

    init{
        getProfile()
    }
}