package com.example.balmax2.data

data class User(
    val id: Long,
    val username: String,
    val password: String,
    val canEditUsers: Boolean,
    val canEditSpots: Boolean,
    val canEditPatioLicenses: Boolean
)
