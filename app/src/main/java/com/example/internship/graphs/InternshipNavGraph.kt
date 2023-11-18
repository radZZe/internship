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
import com.example.internship.ui.profiles.profiel_intern.EditProfileIntern
import com.example.internship.ui.profiles.profile_company.EditProfileCompany
import com.example.internship.ui.profiles.profile_company.ProfileCompany
import com.example.internship.util.Graphs
import com.example.internship.util.Screens


fun NavGraphBuilder.internshipNavGraph (navController: NavController){
    navigation(
        startDestination = Screens.ProfileCompany.route,
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
            ProfileIntern(
                onBackNavigate = {
                    navController.popBackStack()
                },
                onEditNavigate = {
                    navController.navigate(Screens.ProfileEditIntern.route)
                }
            )
        }
        composable(Screens.ProfileEditIntern.route){backStack->
            EditProfileIntern(
                onBackNavigate = {
                    navController.popBackStack()
                },
            )
        }
        composable(Screens.ProfileCompany.route){backStack->
            ProfileCompany(
                onBackNavigate = {
                    navController.popBackStack()
                },
                onEditNavigate = {
                    navController.navigate(Screens.ProfileEditCompany.route)
                }
            )
        }
        composable(Screens.ProfileEditCompany.route){backStack->
            EditProfileCompany(
                onBackNavigate = {
                    navController.popBackStack()
                },
            )
        }
    }
}