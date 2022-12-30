package com.example.androidproiect22.api.createTask

import com.google.gson.annotations.SerializedName

data class CreateTaskRequest(
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("assignedToUserId")
    var assigneeId : Int,
    @SerializedName("priority")
    var priority : Int,
    @SerializedName("deadline")
    var deadline : Long,
    @SerializedName("departmentId")
    var departmentId: Int,
    @SerializedName("status")
    var status : Int
)