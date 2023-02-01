package com.example.androidproiect22.model

data class Activity(
    var id: Int,
    var title: String,
    var description : String,
    var createdTime : Long,
    var creatorId: Int,
    var creator: User?,
    var assigneeId: Int,
    var assignee : User?,
    var priority: Int,
    var deadline: Long,
    var departmentId: Int,
    var department: Department?,
)