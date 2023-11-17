package com.example.internship.models

import kotlinx.serialization.Serializable

@Serializable
data class BaseInfo(
    val name: String,
    val surname: String,
    val lastName: String,
    val city: String,
    val photo: String = "",
    val email: String,
    val password: String,
    val status: String,
    val contact: String,
    val aboutUser: String,
    val experience: String
)
