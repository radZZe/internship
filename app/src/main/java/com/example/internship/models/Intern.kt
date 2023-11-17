package com.example.internship.models

import kotlinx.serialization.Serializable

@Serializable
data class Intern(
    val baseInfo: BaseInfo,
    val speciality: String,
    val sex: String,
    val age: Int,
    val skills: MutableList<String> = mutableListOf()
)
