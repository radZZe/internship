package com.example.internship.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.util.Date

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
    val startDate: Date?=null,
    val imageUrl:String?=null,
    val teamLead:String?,
    val specialities:List<String>,
    val additionalInfo: String?=null,
    val feedbacks:List<Feedback>? = null
)

enum class InternshipType(type:String){
    Task("task"),
    Internship("internship"),
    Project("project")
}


enum class InternshipStatus(status:String){
    @SerializedName("in progress")
    InProgress("in progress"),
    @SerializedName("preparing")
    Preparing("preparing"),
    @SerializedName("archive")
    Archive("archive")
}