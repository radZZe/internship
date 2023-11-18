package com.example.internship.util

sealed class Screens(
    val route:String
){
    object InternshipCatalog:Screens(route = "internship_catalog")
    object InternshipCard:Screens(route = "internship_card")
    object ProfileIntern:Screens(route = "profile_intern")
    object ProfileEditIntern:Screens(route = "profile_edit_intern")
    object ProfileCompany:Screens(route = "profile_company")
    object ProfileEditCompany:Screens(route = "profile_edit_company")
    object PersonalDataScreen:Screens(route = "personal_data")
    object FilterInternships:Screens(route = "filter internships")
}