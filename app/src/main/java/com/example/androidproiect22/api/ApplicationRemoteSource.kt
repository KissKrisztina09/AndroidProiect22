package com.example.androidproiect22.api

import com.example.androidproiect22.api.createTask.CreateTaskRequest
import com.example.androidproiect22.api.createTask.CreateTaskResponse
import com.example.androidproiect22.api.department.DepartmentResponse
import com.example.androidproiect22.api.edit_profile.EditProfileRequest
import com.example.androidproiect22.api.edit_profile.EditProfileResponse
import com.example.androidproiect22.api.login.LoginRequest
import com.example.androidproiect22.api.login.LoginResponse
import com.example.androidproiect22.api.user.UserResponse
import com.example.androidproiect22.api.task.TaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApplicationRemoteSource {
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/user")
    suspend fun getProfileDetails(@Header("token") authToken : String): Response<UserResponse>

    @GET("/task/getTasks")
    suspend fun getAllTasks(@Header("token") authToken : String): Response<List<TaskResponse>>
    companion object {
        fun getApi(): ApplicationRemoteSource? {
            return ApiClient.client?.create(ApplicationRemoteSource::class.java)
        }
    }

    @GET("/users")
    suspend fun getAllUsers(@Header("token") authToken : String): Response<List<UserResponse>>

    @GET("/department")
    suspend fun getDepartments(@Header("token") authToken: String) : Response<List<DepartmentResponse>>

    @POST("/task/create")
    suspend fun createTask(@Body createTaskRequest: CreateTaskRequest,@Header("token") authToken: String) : Response<CreateTaskResponse>

    @POST("users/updateProfile")
    suspend fun updateProfile(@Body editProfileRequest: EditProfileRequest, @Header("token") authToken: String) : Response<EditProfileResponse>
}