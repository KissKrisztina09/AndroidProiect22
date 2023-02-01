package com.example.androidproiect22.repo

import com.example.androidproiect22.api.login.LoginRequest
import com.example.androidproiect22.api.login.LoginResponse
import com.example.androidproiect22.api.login.UserApi
import retrofit2.Response

object UserRepository {
    fun getAuthToken() : String {
        return SharedPref.read(SharedPref.AUTH_TOKEN, "")!!
    }

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse>? {
        return  UserApi.getApi()?.loginUser(loginRequest = loginRequest)
    }
}