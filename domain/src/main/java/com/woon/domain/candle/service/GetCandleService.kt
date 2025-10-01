package com.woon.domain.candle.service

import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.SecondCandle
import com.woon.domain.candle.repository.CandleRepository
import com.woon.domain.candle.usecase.GetCandleUseCase
import javax.inject.Inject

class GetCandleService
@Inject constructor(
    private val candleRepository: CandleRepository
) : GetCandleUseCase {

    override suspend fun getSecond() : List<SecondCandle> {
        return candleRepository.getSecondCandle()
    }

    override suspend fun getMinute(unit: Int): List<MinuteCandle> {
        return candleRepository.getMinuteCandle(unit)
    }
}