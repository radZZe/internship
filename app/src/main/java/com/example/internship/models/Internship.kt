package com.example.internship.models

import android.hardware.SensorAdditionalInfo
import kotlinx.serialization.Serializable

@Serializable
data class Internship(
    val id:String,
    val name:String,
    val description:String,
    val duration:String,
    val department:String? = null,
    val peopleCnt:Int,
    val salary:Int? = null,
    val type:InternshipType,
    val status:InternshipStatus,
   // val imageUrl:String
    val teamLead:String?,
    val specialities:List<String>,
    val additionalInfo: String?=null
)

enum class InternshipType(type:String){
    Task("task"),
    Internship("internship"),
    Project("project")
}


enum class InternshipStatus(status:String){
    InProgress("in progress"),
    Preparing("preparing"),
    Archive("archive")
}