package com.example.flashcards.feature.examSubject

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.flashcards.composable.SubjectExam
import com.example.flashcards.feature.examSubject.examSubjectState.ExamSubjectState

@Composable
fun ExamSubjectScreen(
    state: ExamSubjectState,
    onDoneClicked: () -> Unit
){
    SubjectExam(flashCardList = state.flashCards, onDoneClicked)
}