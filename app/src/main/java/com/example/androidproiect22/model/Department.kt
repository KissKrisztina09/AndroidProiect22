package com.example.androidproiect22.model

data class Department(
    var id: Int,
    var name: String
) {
    override fun toString(): String {
        return name
    }
}
