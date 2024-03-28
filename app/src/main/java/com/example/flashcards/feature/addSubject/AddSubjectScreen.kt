package com.example.flashcards.feature.addSubject

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashcards.feature.addSubject.addSubjectViewModel.AddSubjectViewModel
import java.lang.Exception

@Composable
fun AddSubjectScreen(
    navController: NavController,
    viewModel: AddSubjectViewModel = hiltViewModel()
    ) {
    var subjectName by rememberSaveable { mutableStateOf("") }
     Scaffold(
         topBar = {
             Button(onClick = { navController.navigate("home_route") }) {
                 Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go back")
             }
         }
     ) {
         innerPadding ->
         Column(
             modifier = Modifier.padding(innerPadding),
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             HorizontalDivider(thickness = 1.dp)
             Text(
                 text = "Add new subject",
                 style = MaterialTheme.typography.headlineLarge,
                 fontSize = 24.sp,
                 fontWeight = FontWeight.Bold
             )
             Spacer(modifier = Modifier.padding(8.dp))
             Text(
                 text = "Subject Name",
                 style = MaterialTheme.typography.bodyLarge,
                 fontSize = 20.sp
             )
             Spacer(modifier = Modifier.padding(8.dp))
             TextField(
                 modifier = Modifier.fillMaxWidth(),
                 value = subjectName,
                 onValueChange = {
                     subjectName = it
                 },
                 placeholder = {
                     Text(text = "Enter subject name")
                 }
             )

             Spacer(modifier = Modifier.padding(16.dp))

             Button(
                 modifier = Modifier
                     .fillMaxWidth()
                     .padding(vertical = 16.dp)
                     .height(56.dp),
                 onClick = {
                     try {
                         viewModel.addSubject(subjectName)
                         navController.navigate("home_route")
                     } catch (_: Exception) {
                     }
                 },
                 ) {
                 Text(
                     text = "Add Subject",
                     style = MaterialTheme.typography.bodyLarge
                 )
             }
         }
     }
}