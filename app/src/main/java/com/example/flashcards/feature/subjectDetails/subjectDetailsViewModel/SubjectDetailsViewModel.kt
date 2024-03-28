package com.example.flashcards.feature.subjectDetails.subjectDetailsViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.subjectDetails.subjectDetailsState.SubjectDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId
import javax.inject.Inject

@HiltViewModel
class SubjectDetailsViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(SubjectDetailsState())
        private set
    private val realm = FlashCardApp.realm


    fun getFlashCards(subjectId: String) {

        viewModelScope.launch {
            val subjectDetails = realm
                .query<Subject>(
                    "_id == $0",
                    ObjectId(subjectId)
                ).first()
            subjectDetails.asFlow().collect{ subject ->
                state = subject.obj?.flashCards?.toList()?.let {
                    state.copy(
                        flashCardList = it,
                        subjectId = subjectId
                    )
                }!!
            }
        }
    }

}