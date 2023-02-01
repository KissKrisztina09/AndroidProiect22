package com.example.androidproiect22.service

import com.example.androidproiect22.api.login.LoginRequest
import com.example.androidproiect22.model.User
import com.example.androidproiect22.repo.UserRepository
import com.example.androidproiect22.api.ApplicationRemoteSource
import com.example.androidproiect22.api.edit_profile.EditProfileRequest
import com.example.androidproiect22.api.login.LoginResponse
import com.example.androidproiect22.api.mappers.DtoMappers
import com.example.androidproiect22.repo.SharedPref
import retrofit2.Response

object UserService {
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        val response = ApplicationRemoteSource.getApi()?.loginUser(loginRequest = loginRequest)
        if (response?.isSuccessful == true) {
            if (response.body()?.token != null) {
                SharedPref.write(SharedPref.AUTH_TOKEN, response.body()!!.token)
            }
        }
        return response
    }

    fun getAuthToken() : String {
        return UserRepository.getAuthToken()
    }

    suspend fun getProfileDetails() : User? {
        val authToken: String = getAuthToken()

        val response = ApplicationRemoteSource.getApi()?.getProfileDetails( authToken = authToken)
        if (response?.isSuccessful == true) {
            return DtoMappers.userFromDto(response.body()!!)
        }
        return null
    }

    suspend fun getUserById(id: Int) : User? {
        val authToken: String = getAuthToken()

        val response = ApplicationRemoteSource.getApi()?.getAllUsers( authToken = authToken)
        if (response?.isSuccessful == true) {
            val users : List<User> =  response.body()!!.map {DtoMappers.userFromDto(it)}
            return users.find{it.userId == id}
        }
        return null
    }

    suspend fun getAllUsers() : List<User>? {
        val authToken: String = getAuthToken()

        val response = ApplicationRemoteSource.getApi()?.getAllUsers( authToken = authToken)
        if (response?.isSuccessful == true) {
            return response.body()!!.map { DtoMappers.userFromDto(it) }
        }
        return null
    }

    suspend fun updateProfile(authToken: String,lastName: String, firstName : String, location: String, phoneNumber : String, imageUrl : String) : Boolean {
        val updateRequest = EditProfileRequest(lastName, firstName, location, phoneNumber, imageUrl)
        val response = ApplicationRemoteSource.getApi()?.updateProfile(editProfileRequest = updateRequest, authToken = authToken)
        if (response?.isSuccessful == true) {
            if (response.body()!!.message == "Success") {
                return true
            }
            return false
        }
        return false
    }
}