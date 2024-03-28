package com.example.flashcards.data.entity

import io.realm.kotlin.types.EmbeddedRealmObject

class FlashCard: EmbeddedRealmObject {
    var question: String = ""
    var answer: String = ""
    var subject: Subject? = null
}