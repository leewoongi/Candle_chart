package com.woon.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.woon.home.ui.component.ChartComponent
import com.woon.home.ui.component.YAxisComponent
import com.woon.home.ui.state.HomeUiState

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState.Success,
    onZoom: (Float) -> Unit = {},
    onDrag: (Offset) -> Unit = {},
    onSizeChanged: (IntSize) -> Unit = {},
    onViewportChanged: (Float, Float, Float) -> Unit = { _, _, _ -> }
) {
    var chartSize by remember { mutableStateOf(IntSize.Zero) }
    
    // 뷰포트 가격 범위가 있으면 사용, 없으면 전체 가격 범위 사용
    val displayPriceRange = uiState.viewportPriceRange ?: uiState.priceRange
    
    Row(
        modifier = modifier.fillMaxSize()
    ) {
        // 가격 레이블 (Y축)
        YAxisComponent(
            modifier = Modifier.weight(0.2f),
            priceRange = displayPriceRange,
            chartHeight = if (chartSize.height > 0) chartSize.height.dp else 400.dp
        )
        
        // 차트
        Box(modifier = Modifier.weight(0.8f)) {
            ChartComponent(
                modifier = Modifier.fillMaxSize(),
                uiModel = uiState.uiModel,
                priceRange = displayPriceRange,
                onZoom = { onZoom(it) },
                onDrag = { onDrag(it) },
                onSizeChanged = { size ->
                    chartSize = size
                    onSizeChanged(size)
                },
                onViewportChanged = onViewportChanged
            )
        }
    }
}
