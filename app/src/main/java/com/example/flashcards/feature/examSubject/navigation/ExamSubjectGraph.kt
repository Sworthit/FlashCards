package com.example.flashcards.feature.examSubject.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flashcards.feature.subjectDetails.navigation.SubjectDetailsRoute

fun NavGraphBuilder.addExamSubjectGraph(navController: NavController){
    composable(
        route = "subject/{subjectId}/exam",
        arguments = listOf(navArgument("subjectId") {type = NavType.StringType})
    ) { backStackEntry ->
        val subjectId = backStackEntry.arguments?.getString("subjectId")
        ExamSubjectRoute(subjectId = subjectId!!, navController = navController)
    }
}