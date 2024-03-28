package com.example.flashcards.feature.addFlashCard.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.feature.addFlashCard.AddFlashCardScreen
import com.example.flashcards.feature.addFlashCard.addFlashCardViewModel.AddFlashCardViewModel
import com.example.flashcards.feature.addSubject.AddSubjectScreen

@Composable
fun AddFlashCardRoute(
    navController: NavController,
    subjectId: String,
    viewModel: AddFlashCardViewModel = hiltViewModel()
) {
    viewModel.setSubjectId(subjectId)
    AddFlashCardScreen(navController = navController, viewModel)
}