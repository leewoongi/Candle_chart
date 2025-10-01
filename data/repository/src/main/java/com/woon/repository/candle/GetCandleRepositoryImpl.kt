package com.woon.repository.candle

import com.woon.datasource.remote.candle.RemoteCandleDataSource
import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.SecondCandle
import com.woon.domain.candle.repository.CandleRepository
import com.woon.repository.candle.mapper.toDomain
import javax.inject.Inject

class GetCandleRepositoryImpl
@Inject constructor(
    private val remoteCandleDataSource: RemoteCandleDataSource
): CandleRepository {
    override suspend fun getSecondCandle(): List<SecondCandle> {
        TODO("Not yet implemented")
    }

    override suspend fun getMinuteCandle(unit: Int): List<MinuteCandle> {
        return remoteCandleDataSource.getMinuteCandle(240).map {
            it.toDomain()
        }
    }
}