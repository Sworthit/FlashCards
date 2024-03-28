package com.example.flashcards.feature.flashCard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.flashcards.feature.flashCard.flashCardState.FlashCardState
import com.example.flashcards.feature.flashCard.flashCardViewModel.FlashCardViewModel

@Composable
fun FlashCardScreen(
    viewModel: FlashCardViewModel,
    navController: NavController
) {
    var isEdit by rememberSaveable {
        mutableStateOf(false)
    }

    var isQuestionValid by rememberSaveable{ mutableStateOf(false) }
    var isAnswerValid by rememberSaveable{ mutableStateOf(false) }
    var question by rememberSaveable {
        mutableStateOf("")
    }
    var answer by rememberSaveable {
        mutableStateOf("")
    }

    question = viewModel.state.question
    answer = viewModel.state.answer

    if (isEdit){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Question")
                    TextField(
                        value = question,
                        onValueChange = {
                            question = it
                            isQuestionValid = it.isNotEmpty()
                        },
                        isError = question.isEmpty(),
                    )
                }
            }
            Card{
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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = { isEdit = false }
                ){
                    Text(text = "Abort")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    onClick = {
                        viewModel.updateFlashCard(question, answer)
                        isEdit = false
                    }
                ){
                    Text(text = "Save Changes")
                }
            }
        }
    }
    else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Question")
                    TextField(
                        value = viewModel.state.question,
                        enabled = false,
                        onValueChange = {},
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                }
            }
            Card {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Answer")
                    TextField(
                        value = viewModel.state.answer,
                        enabled = false,
                        onValueChange = {},
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    onClick = { isEdit = true }
                ){
                    Text(text = "Edit")
                }

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = {
                        viewModel.deleteFlashCard()
                        navController.navigate("subject/${viewModel.state.subjectId}")
                    }
                ){
                    Text(text = "Delete")
                }
            }
        }
    }

}