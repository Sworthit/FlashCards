package com.example.flashcards.feature.addSubject.addSubjectViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcards.FlashCardApp
import com.example.flashcards.data.entity.Subject
import com.example.flashcards.feature.addSubject.addSubjectState.AddSubjectState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSubjectViewModel @Inject constructor() : ViewModel() {
    private var state = AddSubjectState()

    fun addSubject(subjectName: String) {
        if(subjectName.isNotEmpty()) {
            state = state.copy(
                name = subjectName
            )
            viewModelScope.launch {
                FlashCardApp.realm.writeBlocking {
                    val subject = Subject().apply {
                        name = state.name
                    }
                    copyToRealm(subject, UpdatePolicy.ALL)
                }
            }
        }
    }
}