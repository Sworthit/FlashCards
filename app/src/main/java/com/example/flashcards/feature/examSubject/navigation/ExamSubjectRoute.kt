package com.example.flashcards.feature.examSubject.navigation

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.feature.examSubject.ExamSubjectScreen
import com.example.flashcards.feature.examSubject.examSubjectViewModel.ExamSubjectViewModel

@Composable
fun ExamSubjectRoute(
    subjectId: String,
    navController: NavController,
    viewModel: ExamSubjectViewModel = hiltViewModel()
) {
    viewModel.setUpState(subjectId = subjectId)
    ExamSubjectScreen(state = viewModel.state) {
        navController.navigate("subject/$subjectId")
    }
}