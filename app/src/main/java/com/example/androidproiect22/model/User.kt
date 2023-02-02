package com.example.androidproiect22.model


data class  User (
    var userId: Int,
    var lastName: String,
    var firstName: String,
    var email : String,
    var location : String,
    var phoneNumber : String,
    var departmentId : Int,
    var imageUrl: String?
        ) {
    override fun toString(): String {
        return firstName + " " + lastName
    }
}