package com.example.androidproiect22.api.task

import com.google.gson.annotations.SerializedName

data class TaskResponse (
    @SerializedName("ID")
    var taskId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("created_time")
    var createdTime : Long,
    @SerializedName("created_by_user_ID")
    var creatorId : Int,
    @SerializedName("asigned_to_user_ID")
    var assigneeId : Int,
    @SerializedName("priority")
    var priority : Int,
    @SerializedName("deadline")
    var deadline : Long,
    @SerializedName("department_ID")
    var departmentId: Int,
    @SerializedName("status")
    var status : Int
)