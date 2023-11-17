package com.example.internship.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.internship.ui.theme.internship_card.InternshipCard
import com.example.internship.ui.theme.internship_catalog.InternshipCatalog
import com.example.internship.util.Graphs
import com.example.internship.util.Screens


fun NavGraphBuilder.internshipNavGraph (navController: NavController){
    navigation(
        startDestination = Screens.InternshipCatalog.route,
        route = Graphs.Internship.route
    ){
        composable(Screens.InternshipCatalog.route){
            InternshipCatalog(
                onNavigateToCard = {
                    navController.navigate("${Screens.InternshipCard.route}/$it")
                }
            )
        }

        composable("${Screens.InternshipCard.route}/{internshipId}"){backStack->
            InternshipCard(internshipId = backStack.arguments?.getString("internshipId")?:"")
        }
    }
}