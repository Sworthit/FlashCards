package com.example.flashcards.feature.addFlashCard.addFlashCardViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.FlashCard
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.addFlashCard.addFlashCardState.AddFlashCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class AddFlashCardViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(AddFlashCardState())
        private set

    fun setSubjectId(subjectId: String) {
        state = state.copy(
            subjectId = subjectId
        )
    }

    private fun setFlashCardData(question: String, answer: String) {
        state = state.copy(
            question = question,
            answer = answer,
        )
    }

    fun saveFlashCard(questionAdded: String, answerAdded: String) {
        val subjectObjectId = ObjectId(state.subjectId)


        setFlashCardData(questionAdded, answerAdded)
        viewModelScope.launch {
            FlashCardApp.realm.write {
                val flashCard = FlashCard().apply{
                    answer = state.answer
                    question = state.question
                }
                val subject = this
                    .query<Subject>("_id == $0", subjectObjectId)
                    .first()
                    .find()
                subject?.flashCards?.add(flashCard)
            }
        }
    }
}