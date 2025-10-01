package com.woon.datasource.remote.candle

import com.woon.datasource.remote.candle.api.CandleApi
import com.woon.datasource.remote.candle.response.MinuteResponse
import com.woon.datasource.remote.candle.response.SecondResponse
import javax.inject.Inject

class RemoteCandleDataSourceImpl
@Inject constructor(
    private val candleApi: CandleApi
): RemoteCandleDataSource {

    override suspend fun getSecondCandle(): List<SecondResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMinuteCandle(unit: Int): List<MinuteResponse> {
        return candleApi.getMinuteCandle(
            unit = 240,
            market = "KRW-BTC",
            to = "",
            count = 200
        )
    }
}