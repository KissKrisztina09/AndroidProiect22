package com.example.androidproiect22.api.createTask

import com.google.gson.annotations.SerializedName

data class CreateTaskResponse(
    @SerializedName("message")
    var message : String,
)
