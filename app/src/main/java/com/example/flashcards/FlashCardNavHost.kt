package com.example.flashcards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.flashcards.feature.addFlashCard.navigation.addNewFlashCardGraph
import com.example.flashcards.feature.addSubject.navigation.addSubjectGraph
import com.example.flashcards.feature.examSubject.navigation.addExamSubjectGraph
import com.example.flashcards.feature.flashCard.navigation.addFlashCardGraph
import com.example.flashcards.feature.home.homeViewModel.HomeViewModel
import com.example.flashcards.feature.home.navigation.homeGraph
import com.example.flashcards.feature.subjectDetails.navigation.addSubjectDetailsGraph

@Composable
fun FlashCardNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    NavHost(
        navController = navController,
        startDestination = "home_route",
        modifier = modifier) {
        homeGraph(navController, homeViewModel)
        addSubjectGraph(navController)
        addSubjectDetailsGraph(navController)
        addNewFlashCardGraph(navController)
        addExamSubjectGraph(navController)
        addFlashCardGraph(navController)
    }
}