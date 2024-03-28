package com.example.flashcards.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.flashcards.data.entity.FlashCard
import com.example.flashcards.ui.theme.Purple80
import com.example.flashcards.ui.theme.PurpleGrey40

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SubjectExam(flashCardList: List<FlashCard>, onDoneClicked: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { flashCardList.size })
    var isClicked by rememberSaveable{ mutableStateOf(false) }
    val animatedAlpha by animateFloatAsState(targetValue = if (isClicked) 1.0f else 0f, label = "alpha", animationSpec = tween(1250))
    HorizontalPager(state = pagerState) { page ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FlashCardsDisplay(text = flashCardList[page].question) {}
            ExamCard(answer = flashCardList[page].answer) { clicked ->
                isClicked = clicked
            }
            if (!pagerState.canScrollForward && isClicked) {
                Box(modifier = Modifier
                    .size(75.dp)
                    .graphicsLayer {
                        alpha = animatedAlpha
                    }
                    .clip(RoundedCornerShape(100.dp))
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .clickable { onDoneClicked() },
                    contentAlignment = Alignment.Center) {
                    Text(text = "Done")
                }
            }
        }
    }
}