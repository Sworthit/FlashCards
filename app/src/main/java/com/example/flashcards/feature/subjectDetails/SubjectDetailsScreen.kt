package com.example.flashcards.feature.subjectDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.composable.FlashCardsDisplay
import com.example.flashcards.feature.subjectDetails.subjectDetailsViewModel.SubjectDetailsViewModel

@Composable
fun SubjectDetailsScreen(
    navController: NavController,
    viewModel: SubjectDetailsViewModel = hiltViewModel()
) {
    val bottomBarState = rememberSaveable { mutableStateOf(false) }
    val lazyListState = rememberLazyStaggeredGridState()

    if (viewModel.state.flashCardList.isNotEmpty())
        bottomBarState.value = true
    Scaffold(
        bottomBar = {
                    if (bottomBarState.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { navController.navigate("home_route") {
                                popUpTo(navController.graph.id)
                            } }) {
                                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
                            }
                            Button(onClick = { navController.navigate("subject/${viewModel.state.subjectId}/exam") }) {
                                Text(text = "Test yourself")
                            }
                            Button(onClick = { navController.navigate("add_fc_route/${viewModel.state.subjectId}") }) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add new subject")
                            }
                        }
                    }
        },
    ) {
        innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            if (viewModel.state.flashCardList.isEmpty()) {
                Text(
                    text = "No flash cards found... How are you supposed to learn?",
                    textAlign = TextAlign.Center
                )
                Button(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    onClick = { navController.navigate("add_fc_route/${viewModel.state.subjectId}") },

                ) {
                    Text(text = "Add flash card")
                }
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    state = lazyListState,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalItemSpacing = 10.dp,
                    content = {
                        itemsIndexed(
                            items = viewModel.state.flashCardList
                        ) { index, flashCard ->
                            FlashCardsDisplay(text = flashCard.question) {
                                navController.navigate("subject/${viewModel.state.subjectId}/$index",)
                            }
                        }
                    }
                )
            }
        }
    }
}