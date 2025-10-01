package com.woon.home.model

import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.SecondCandle

internal fun SecondCandle.toUiModel() : CandleUiModel{
    return CandleUiModel(
        high = high.toDouble(),
        low = low.toDouble(),
        open = open.toDouble(),
        trade = trade.toDouble()
    )
}

internal fun MinuteCandle.toUiModel() : CandleUiModel{
    return CandleUiModel(
        high = high.toDouble(),
        low = low.toDouble(),
        open = open.toDouble(),
        trade = trade.toDouble()
    )
}