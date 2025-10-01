package com.woon.home.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun YAxisComponent(
    modifier: Modifier = Modifier,
    priceRange: Pair<Double, Double>,
    chartHeight: Dp
){
    val (minPrice, maxPrice) = priceRange
    val labelCount = 10 // 레이블 개수를 늘림

    Box(
        modifier = modifier
            .height(chartHeight)
            .padding(start = 8.dp)
    ) {
        repeat(labelCount + 1) { index ->
            val ratio = index.toFloat() / labelCount
            val price = maxPrice - (maxPrice - minPrice) * ratio
            val yOffset = chartHeight * (1f - ratio)

            Box(
                modifier = Modifier
                    .offset(y = yOffset - 8.dp) // 텍스트 중앙 정렬을 위한 오프셋 조정
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                // 가격 표시
                Text(
                    text = formatKRWPrice(price),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }
        }
    }
}

// 원화 전용 포맷팅 함수
private fun formatKRWPrice(price: Double): String {
    return when {
        price >= 100_000_000 -> {
            val eok = (price / 100_000_000).toInt()
            val man = ((price % 100_000_000) / 10_000).toInt()
            if (man == 0) "${eok}억" else "${eok}억${man}만"
        }
        price >= 10_000 -> {
            val man = (price / 10_000).toInt()
            val remainder = ((price % 10_000) / 1_000).toInt()
            if (remainder == 0) "${man}만" else "${man}만${remainder}천"
        }
        price >= 1_000 -> {
            "${(price / 1_000).toInt()}천"
        }
        else -> "${price.toInt()}"
    }
}