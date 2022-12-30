package com.example.androidproiect22.repo

object UserRepository {
    fun getAuthToken() : String {
        return SharedPref.read(SharedPref.AUTH_TOKEN, "")!!
    }
}