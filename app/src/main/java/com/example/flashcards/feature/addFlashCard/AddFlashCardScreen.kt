package com.example.flashcards.feature.addFlashCard

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcards.feature.addFlashCard.addFlashCardState.AddFlashCardState
import com.example.flashcards.feature.addFlashCard.addFlashCardViewModel.AddFlashCardViewModel

@Composable
fun AddFlashCardScreen(
    navController: NavController,
    viewModel: AddFlashCardViewModel
    ) {
    var question by rememberSaveable {
        mutableStateOf("")
    }
    var answer by rememberSaveable {
        mutableStateOf("")
    }

    var isQuestionValid by rememberSaveable{ mutableStateOf(false) }
    var isAnswerValid by rememberSaveable{ mutableStateOf(false) }

    Scaffold(
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Question?")
                    TextField(
                        value = question,
                        onValueChange = {
                            question = it
                            isQuestionValid = it.isNotEmpty()
                        },
                        isError = question.isEmpty()
                    )
                }
            }
            Card(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Answer")
                    TextField(
                        value = answer,
                        onValueChange = {
                            answer = it
                            isAnswerValid = it.isNotEmpty()
                        },
                        isError = answer.isEmpty()
                    )
                }
            }
            
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.saveFlashCard(question, answer)
                    navController.navigate("subject/${viewModel.state.subjectId}")
                },
                enabled = isQuestionValid && isAnswerValid
            ) {
                Text(text = "Save")
            }
        }
    }
}