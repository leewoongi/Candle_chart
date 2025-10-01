package com.woon.domain.candle.repository

import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.SecondCandle

interface CandleRepository {
    suspend fun getSecondCandle() : List<SecondCandle>
    suspend fun getMinuteCandle(unit: Int) : List<MinuteCandle>
}