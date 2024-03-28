package com.example.flashcards.feature.examSubject.examSubjectViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.examSubject.examSubjectState.ExamSubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class ExamSubjectViewModel @Inject constructor(): ViewModel() {
    var state by mutableStateOf(ExamSubjectState())
    private val realm = FlashCardApp.realm

    fun setUpState(subjectId: String) {
        viewModelScope.launch {
            val subjectDetails = realm
                    .query<Subject>(
                "_id == $0",
                ObjectId(subjectId)
            ).first()
            subjectDetails.asFlow().collect { subject ->
                state = subject.obj?.flashCards?.toList()?.let {
                    state.copy(
                        flashCards = it,
                        subjectId = subjectId
                    )
                }!!
            }
        }
    }
}