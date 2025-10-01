package com.woon.datasource.remote.candle

import com.woon.datasource.remote.candle.response.MinuteResponse
import com.woon.datasource.remote.candle.response.SecondResponse

interface RemoteCandleDataSource {
    suspend fun getSecondCandle() : List<SecondResponse>
    suspend fun getMinuteCandle(unit: Int): List<MinuteResponse>
}