package com.woon.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.woon.home.ui.SuccessScreen
import com.woon.home.ui.state.HomeUiState
import com.woon.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<HomeViewModel>()
    val uiState = viewModel.uiState.collectAsState().value

    when (uiState) {
        HomeUiState.Loading -> {
            Text("데이터 로딩 중...")
        }
        is HomeUiState.Success -> {
            val candles = uiState.uiModel
            if (candles.isEmpty()) {
                Text("데이터 없음...")
            } else {
                SuccessScreen(
                    modifier = modifier.fillMaxSize(),
                    uiState = uiState,
                    onZoom = {},
                    onDrag = {},
                    onSizeChanged = {  },
                    onViewportChanged = { canvasWidth, offsetX, zoom ->
                        viewModel.updateViewport(canvasWidth, offsetX, zoom)
                    }
                )
            }
        }
    }
}
