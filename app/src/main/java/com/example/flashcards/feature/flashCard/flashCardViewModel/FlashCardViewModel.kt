package com.example.flashcards.feature.flashCard.flashCardViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.FlashCard
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.flashCard.flashCardState.FlashCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import javax.inject.Inject

@HiltViewModel
class FlashCardViewModel @Inject constructor(): ViewModel(){
    var state by mutableStateOf(FlashCardState())
        private set

    fun setFcData(subjectId: String, fcIndex: String) {
        viewModelScope.launch {
            val subjectDetails = FlashCardApp.realm
                .query<Subject>(
                    "_id == $0",
                    BsonObjectId(subjectId)
                ).first()
            subjectDetails.asFlow().collect{ subject ->
                if (subject.obj?.flashCards?.isNotEmpty() == true && subject.obj?.flashCards?.size!! > fcIndex.toInt()){
                    state = subject.obj?.flashCards?.get(fcIndex.toInt())?.let {
                        state.copy(
                            question = it.question,
                            answer = it.answer,
                            flashCardIndex = fcIndex,
                            subjectId = subjectId
                        )
                    }!!
                }
            }
        }
    }

    private fun setFlashCardData(question: String, answer: String) {
        state = state.copy(
            question = question,
            answer = answer,
        )
    }

    fun updateFlashCard(questionAdded: String, answerAdded: String) {
        val subjectObjectId = BsonObjectId(state.subjectId)
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
                subject?.flashCards?.set(state.flashCardIndex.toInt(), flashCard)
            }
        }
    }

    fun deleteFlashCard() {
        viewModelScope.launch {
            FlashCardApp.realm.write {
                val subject = this
                    .query<Subject>("_id == $0", BsonObjectId(state.subjectId))
                    .first()
                    .find()
                subject?.flashCards?.removeAt(state.flashCardIndex.toInt())
            }
        }
    }
}