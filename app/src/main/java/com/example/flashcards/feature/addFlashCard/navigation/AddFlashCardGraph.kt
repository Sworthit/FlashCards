package com.example.flashcards.feature.addFlashCard.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.addNewFlashCardGraph(navController: NavController) {
    composable(
        route = "add_fc_route/{subjectId}",
        arguments = listOf(navArgument("subjectId") {type = NavType.StringType})
        ) { backStackEntry ->
        val subjectId = backStackEntry.arguments?.getString("subjectId")
        AddFlashCardRoute(navController, subjectId!!)
    }
}