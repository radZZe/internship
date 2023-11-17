package com.example.internship.util

sealed class Screens(
    val route:String
){
    object InternshipCatalog:Screens(route = "internship_catalog")
    object InternshipCard:Screens(route = "internship_card")
}