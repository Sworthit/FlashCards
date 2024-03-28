package com.example.flashcards.feature.home.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.feature.home.HomeScreen
import com.example.flashcards.feature.home.homeViewModel.HomeViewModel

@Composable
fun HomeRoute(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
    ) {
    val state = viewModel.state
    HomeScreen(
        navController = navController,
        viewModel = viewModel
    )
}