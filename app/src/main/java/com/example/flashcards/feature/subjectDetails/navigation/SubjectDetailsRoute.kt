package com.example.flashcards.feature.subjectDetails.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.example.flashcards.feature.subjectDetails.SubjectDetailsScreen
import com.example.flashcards.feature.subjectDetails.subjectDetailsViewModel.SubjectDetailsViewModel

@Composable
fun SubjectDetailsRoute(
    navController: NavController,
    viewModel: SubjectDetailsViewModel = hiltViewModel(),
    subjectId: String
    ) {
    viewModel.getFlashCards(subjectId = subjectId)
    SubjectDetailsScreen(navController = navController, viewModel = viewModel)
}