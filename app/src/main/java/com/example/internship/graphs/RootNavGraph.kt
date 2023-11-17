package com.example.internship.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.internship.util.Graphs
import com.example.internship.util.Screens

@Composable
fun RootNavGraph(
    navController:NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Graphs.Internship.route
    ){
        internshipNavGraph(navController = navController)
    }
}