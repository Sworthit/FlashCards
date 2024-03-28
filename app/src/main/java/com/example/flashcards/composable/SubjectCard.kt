package com.example.flashcards.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flashcards.data.entity.Subject
import org.mongodb.kbson.ObjectId

@Composable
fun SubjectCard(subject: Subject, selectedSubject: (ObjectId) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .clickable { selectedSubject(subject._id) }
        ,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = subject.name,
                style = MaterialTheme.typography.headlineLarge,
                )
        }
    }
    
}