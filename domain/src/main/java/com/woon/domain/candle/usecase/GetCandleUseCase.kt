package com.woon.domain.candle.usecase

import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.SecondCandle

interface GetCandleUseCase {
    suspend fun getSecond() : List<SecondCandle> // 초당
    suspend fun getMinute(unit: Int): List<MinuteCandle> // 분당
}