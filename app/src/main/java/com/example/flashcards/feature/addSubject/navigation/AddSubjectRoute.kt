package com.example.flashcards.feature.addSubject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.flashcards.feature.addSubject.AddSubjectScreen

@Composable
fun AddSubjectRoute(navController: NavController) {
    AddSubjectScreen(navController = navController)
}