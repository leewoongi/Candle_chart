package com.woon.home.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GridLineComponent(
    modifier: Modifier = Modifier
){
    Canvas(modifier = modifier) {
        val labelCount = 6

        repeat(labelCount + 1) { index ->
            val ratio = index.toFloat() / labelCount
            val y = size.height * ratio

            // 격자선 그리기
            drawLine(
                color = Color.Gray.copy(alpha = 0.2f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}