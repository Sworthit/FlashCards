package com.example.flashcards.feature.home.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.flashcards.feature.addSubject.navigation.AddSubjectRoute
import com.example.flashcards.feature.home.homeViewModel.HomeViewModel
import com.example.flashcards.feature.subjectDetails.navigation.SubjectDetailsRoute

fun NavGraphBuilder.homeGraph(navController: NavController, homeViewModel: HomeViewModel) {
    composable(route = "home_route") {
        HomeRoute(navController)
    }
    composable(route = "add_subject_route") {
        AddSubjectRoute(navController)
    }
    composable(route = "subject/{subjectId}") { backStackEntry ->
        val subjectId = backStackEntry.arguments?.getString("subjectId")
        SubjectDetailsRoute(navController, subjectId = subjectId!!)
    }
}