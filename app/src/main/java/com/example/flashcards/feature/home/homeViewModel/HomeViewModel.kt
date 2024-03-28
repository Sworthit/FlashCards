package com.example.flashcards.feature.home.homeViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.home.homeState.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mongodb.kbson.BsonObjectId
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        loadSubjects()
    }

    private fun loadSubjects() {
        viewModelScope.launch {
            FlashCardApp.realm
                .query<Subject>()
                .asFlow()
                .map {
                    result ->
                    result.list.toList()
                }
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(),
                    emptyList()
                ).collect{ subjects ->
                    state = state.copy(
                        subjects = subjects
                    )
                }
        }
    }

    fun deleteSubject(subjectId: String) {
        viewModelScope.launch {
            FlashCardApp.realm.write {
                val subjectDetails = this
                    .query<Subject>(
                        "_id == $0",
                        BsonObjectId(subjectId)
                    ).find()
                    .first()
                    .also {
                        delete(it)
                    }
            }
        }
    }
}
