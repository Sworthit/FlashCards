package com.example.flashcards.feature.subjectDetails.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.addSubjectDetailsGraph(navController: NavController){
    composable(
        route = "subject/{subjectId}",
        arguments = listOf(navArgument("subjectId") {type = NavType.StringType})
        ) { backStackEntry ->
        val subjectId = backStackEntry.arguments?.getString("subjectId")
        SubjectDetailsRoute(navController, subjectId = subjectId!!)
    }

}