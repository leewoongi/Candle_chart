package com.woon.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.domain.candle.usecase.CalculatePriceRangeUseCase
import com.woon.domain.candle.usecase.GetCandleUseCase
import com.woon.home.model.toUiModel
import com.woon.home.ui.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getCandleUseCase: GetCandleUseCase,
    private val calculatePriceRangeUseCase: CalculatePriceRangeUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        getMinuteCandle()
    }

    private fun getSecondCandle() {
        viewModelScope.launch {
            val result = getCandleUseCase.getSecond().map { it.toUiModel() }
            val high = result.maxOf { it.high }
            val low = result.minOf { it.low }

            _uiState.value = HomeUiState.Success(
                uiModel = result,
                priceRange = Pair(high, low)
            )
        }
    }

    private fun getMinuteCandle() {
        viewModelScope.launch {
            val result = getCandleUseCase.getMinute(240).map { it.toUiModel() }
            val high = result.maxOf { it.high }
            val low = result.minOf { it.low }

            _uiState.value = HomeUiState.Success(
                uiModel = result,
                priceRange = Pair(high, low)
            )
        }
    }


    fun updateViewport(canvasWidth: Float, offsetX: Float, zoom: Float) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Success) {
            val viewportPriceRange = calculateViewportPriceRange(
                candles = currentState.uiModel,
                canvasWidth = canvasWidth,
                offsetX = offsetX,
                zoom = zoom
            )
            
            _uiState.value = currentState.copy(viewportPriceRange = viewportPriceRange)
        }
    }

    // 보이는 캔들의 price Range 추출
    private fun calculateViewportPriceRange(
        candles: List<com.woon.home.model.CandleUiModel>,
        canvasWidth: Float,
        offsetX: Float,
        zoom: Float
    ): Pair<Double, Double>? {
        if (candles.isEmpty()) return null
        
        val candleWidth = 20f
        val candleSpacing = 4f
        val candleSpaceWidth = candleWidth + candleSpacing
        
        // 화면에 보이는 캔들들의 인덱스 범위 계산
        val visibleStartX = -offsetX / zoom
        val visibleEndX = (canvasWidth - offsetX) / zoom
        
        val startIndex = (visibleStartX / candleSpaceWidth).toInt().coerceAtLeast(0)
        val endIndex = (visibleEndX / candleSpaceWidth).toInt().coerceAtMost(candles.size - 1)
        
        if (startIndex > endIndex) return null
        
        // 보이는 캔들들 중에서 최고가와 최저가 찾기
        val visibleCandles = candles.subList(startIndex, endIndex + 1)
        val maxPrice = visibleCandles.maxOf { it.high }
        val minPrice = visibleCandles.minOf { it.low }
        
        return Pair(maxPrice, minPrice)
    }
}