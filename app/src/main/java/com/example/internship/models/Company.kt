package com.example.internship.models

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val organization: String,
    val archiveVacancies: MutableList<String> = mutableListOf<String>(),
    val activeVacancies: MutableList<String> = mutableListOf<String>(),
    val baseInfo: BaseInfo,
    var rating: Double = 0.0,
    val projects: MutableList<String> = mutableListOf()
)


