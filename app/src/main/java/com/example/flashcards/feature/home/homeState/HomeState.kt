package com.example.flashcards.feature.home.homeState

import com.example.flashcards.data.entity.Subject

data class HomeState (
    val subjects: List<Subject> = emptyList()
)
