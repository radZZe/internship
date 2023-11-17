package com.example.internship.util

sealed class Screens(
    val route:String
){
    object InternshipCatalog:Screens(route = "internship_catalog")
    object InternshipCard:Screens(route = "internship_card")
    object PersonalDataScreen:Screens(route = "personal_data")
    object FilterInternships:Screens(route = "filter internships")
}