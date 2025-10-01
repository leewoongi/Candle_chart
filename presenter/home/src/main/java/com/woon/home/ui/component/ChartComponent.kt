package com.woon.home.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import com.woon.home.extension.drag
import com.woon.home.extension.drawCandle
import com.woon.home.extension.pinchZoom
import com.woon.home.model.CandleUiModel

// 차트를 그리는 컴포넌트
// 1. 캔들
@Composable
fun ChartComponent(
    modifier: Modifier = Modifier,
    uiModel: List<CandleUiModel>,
    priceRange: Pair<Double, Double>,
    onZoom: (Float) -> Unit = {},
    onDrag: (Offset) -> Unit = {},
    onSizeChanged: (IntSize) -> Unit = {},
    onViewportChanged: (Float, Float, Float) -> Unit = { _, _, _ -> }
) {

    var zoom by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier
            .background(color = Color.White)
            .pinchZoom(
                getZoom = { zoom },
                setZoom = { 
                    zoom = it
                    onViewportChanged(canvasSize.width.toFloat(), offset.x, zoom)
                },
                getOffset = { offset },
                setOffset = { 
                    offset = it
                    onViewportChanged(canvasSize.width.toFloat(), offset.x, zoom)
                },
                pinchZoom = { onZoom(zoom) }
            )
            .drag(
                getOffset = { offset },
                setOffset = { 
                    offset = it
                    onViewportChanged(canvasSize.width.toFloat(), offset.x, zoom)
                },
                drag = { onDrag(offset) }
            )
            .onSizeChanged { size ->
                canvasSize = size
                onSizeChanged(size)
                onViewportChanged(size.width.toFloat(), offset.x, zoom)
            }
    ) {
        // 줌/오프셋을 그리기 변환에 적용
        withTransform({
            translate(offset.x, offset.y)
            scale(zoom, zoom)
        }) {
            uiModel.forEachIndexed { index, candle ->
                drawCandle(
                    uiModel = candle,
                    priceRange = priceRange,
                    positionX = candle.getPositionX(index = index)
                )
            }
        }
    }
}
