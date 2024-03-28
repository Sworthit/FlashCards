package com.example.flashcards.feature.addSubject.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.flashcards.feature.home.navigation.HomeRoute

fun NavGraphBuilder.addSubjectGraph(navController: NavController) {
    composable(route = "add_subject_route") {
        AddSubjectRoute(navController)
    }
}