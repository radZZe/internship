package com.example.internship.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.internship.ui.profiles.profiel_intern.ProfileIntern
import com.example.internship.ui.registration.PersonalDataScreen
import com.example.internship.ui.internship_card.InternshipCard
import com.example.internship.ui.internship_catalog.InternshipCatalog
import com.example.internship.ui.internship_catalog.filter_screen.FilterInternshipsScreen
import com.example.internship.util.Graphs
import com.example.internship.util.Screens


fun NavGraphBuilder.internshipNavGraph (navController: NavController){
    navigation(
        startDestination = Screens.ProfileIntern.route,
        route = Graphs.Internship.route
    ){
        composable(Screens.InternshipCatalog.route){
            InternshipCatalog(
                onNavigateToCard = {
                    navController.navigate("${Screens.InternshipCard.route}/$it")
                },
                onNavigateToSearch = {

                }
            )
        }

        composable("${Screens.InternshipCard.route}/{internshipId}"){backStack->
            InternshipCard(internshipId = backStack.arguments?.getString("internshipId")?:"",
                onNavigateBack = {
                    navController.popBackStack()
                })
        }

        composable(Screens.FilterInternships.route){
            FilterInternshipsScreen()
        }

        composable(route = Screens.PersonalDataScreen.route) {
            PersonalDataScreen()
        }
        composable(Screens.ProfileIntern.route){backStack->
            ProfileIntern()
        }
    }
}