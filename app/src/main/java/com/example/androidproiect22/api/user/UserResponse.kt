package com.example.androidproiect22.api.user

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("ID")
    var userId: Int,
    @SerializedName("last_name")
    var lastName: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("email")
    var email : String,
    @SerializedName("type")
    var type : Int,
    @SerializedName("location")
    var location : String?,
    @SerializedName("phone_number")
    var phoneNumber : String?,
    @SerializedName("department_id")
    var departmentId : Int,
    @SerializedName("image")
    var imageUrl: String?
)