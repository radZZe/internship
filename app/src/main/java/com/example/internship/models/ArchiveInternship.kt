package com.example.internship.models

import java.util.Date

data class ArchiveInternship(
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
    // val imageUrl:String
    val teamLead:String?,
    val specialities:List<String>,
    val additionalInfo: String?=null,
    val feedbacks:List<Feedback>,

)
