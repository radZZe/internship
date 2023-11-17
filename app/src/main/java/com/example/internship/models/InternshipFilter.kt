package com.example.internship.models

data class InternshipFilter(
    val speciality: MutableList<String>?=null,
    val duration: MutableList<String>?=null,
    val peopleCnt:MutableList<Int>?=null,
    val type:MutableList<InternshipType>?=null
)
