package com.example.flashcards.feature.examSubject.examSubjectState

import com.example.flashcards.data.entity.FlashCard

data class ExamSubjectState(
    val subjectId: String = "",
    val flashCards: List<FlashCard> = emptyList()
)