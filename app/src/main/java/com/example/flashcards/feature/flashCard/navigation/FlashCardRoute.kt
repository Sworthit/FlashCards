package com.example.flashcards.feature.flashCard.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.feature.flashCard.FlashCardScreen
import com.example.flashcards.feature.flashCard.flashCardViewModel.FlashCardViewModel

@Composable
fun FlashCardRoute(
    subjectId: String,
    navController: NavController,
    flashCardIndex: String,
    viewModel: FlashCardViewModel = hiltViewModel()
    ) {
    viewModel.setFcData(subjectId, flashCardIndex)
    FlashCardScreen(viewModel, navController = navController)
}