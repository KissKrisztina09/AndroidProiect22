package com.example.androidproiect22.api.edit_profile

import com.google.gson.annotations.SerializedName

data class EditProfileRequest(
    @SerializedName("lastName")
    var lastName: String,
    @SerializedName("firstName")
    var firstName: String,
    @SerializedName("location")
    var location : String,
    @SerializedName("phoneNumber")
    var phoneNumber : String,
    @SerializedName("imageUrl")
    var imageUrl : String
)