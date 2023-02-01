package com.example.androidproiect22.api.department

import com.google.gson.annotations.SerializedName

data class DepartmentResponse(
    @SerializedName("ID")
    var id: Int,
    @SerializedName("name")
    var name: String
)
