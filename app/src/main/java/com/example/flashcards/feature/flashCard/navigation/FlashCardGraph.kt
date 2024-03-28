package com.example.flashcards.feature.flashCard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.addFlashCardGraph(navController: NavController) {
    composable(
        route = "subject/{subjectId}/{index}",
        arguments = listOf(
            navArgument("subjectId") {type = NavType.StringType},
            navArgument("index") {type = NavType.StringType}
            )
    ) { backStackEntry ->
        val subjectId = backStackEntry.arguments?.getString("subjectId")
        val flashCardIndex = backStackEntry.arguments?.getString("index")
        FlashCardRoute(subjectId = subjectId!!, flashCardIndex = flashCardIndex!!, navController = navController)
    }
}