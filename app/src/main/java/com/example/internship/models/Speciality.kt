package com.example.internship.models

import androidx.compose.runtime.MutableState
import kotlinx.serialization.Serializable

@Serializable
data class Speciality(
    var title:String,
    var isSelected: MutableState<Boolean>
)
