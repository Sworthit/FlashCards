package com.example.flashcards.feature.subjectDetails.subjectDetailsState

import com.example.flashcards.data.entity.FlashCard

data class SubjectDetailsState(
    var flashCardList: List<FlashCard> = emptyList(),
    val subjectId: String = ""
)
