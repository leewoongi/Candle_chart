package com.woon.home.ui.state

import com.woon.home.model.CandleUiModel

sealed class HomeUiState {
    data class Success(
        val uiModel: List<CandleUiModel>,
        val priceRange: Pair<Double, Double>,
        val viewportPriceRange: Pair<Double, Double>? = null
    ) : HomeUiState()
    data object Loading: HomeUiState()
}
