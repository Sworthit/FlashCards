package com.example.flashcards.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FlashCardsDisplay(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .height(160.dp)
            .width(240.dp)
            .padding(10.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clip(RoundedCornerShape(25.dp))
            .clickable { onClick() },
    ) {
        Text(modifier = Modifier.align(Alignment.Center), text = text)
    }
}

@Preview(showBackground = true)
@Composable
private fun FcPreview() {
    FlashCardsDisplay(text = "answer") {}
}