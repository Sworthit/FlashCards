package com.example.flashcards.feature.addFlashCard.addFlashCardState

import com.example.flashcards.data.entity.Subject

data class AddFlashCardState(
    val question: String = "",
    val answer: String = "",
    val subjectId: String = ""
)
