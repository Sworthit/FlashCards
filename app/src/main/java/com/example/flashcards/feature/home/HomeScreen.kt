package com.example.flashcards.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.flashcards.composable.SubjectCard
import com.example.flashcards.composable.SwipeToDeleteContainer
import com.example.flashcards.feature.home.homeViewModel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navController.navigate("add_subject_route")
                },
            ) {
                Icon( imageVector = Icons.Filled.Add, contentDescription = "Add Icon" )
            }
        }
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (viewModel.state.subjects.isEmpty()) {
                Text(text = "No Subjects Available, add one to start learning")
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),

                    ) {
                    items(
                        items = viewModel.state.subjects
                    ) { subject ->
                        SwipeToDeleteContainer(item = subject, onDelete = {viewModel.deleteSubject(subject._id.toHexString())}) {
                            SubjectCard(subject) {
                                navController.navigate("subject/${subject._id.toHexString()}")
                            }
                        }
                    }
                }
            }
        }
    }

}
