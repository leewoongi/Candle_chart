package com.woon.repository.candle.mapper

import com.woon.datasource.remote.candle.response.MinuteResponse
import com.woon.datasource.remote.candle.response.SecondResponse
import com.woon.domain.candle.entity.MinuteCandle
import com.woon.domain.candle.entity.Money
import com.woon.domain.candle.entity.SecondCandle
import com.woon.domain.candle.entity.constant.Market
import com.woon.repository.candle.extension.toDate

internal fun SecondResponse.toDomain(): SecondCandle {
    return SecondCandle(
        market = Market.fromCode(this.market) ?: Market.KRW_BTC,
        time = this.candleDateTimeUtc.toDate(),
        open = Money(this.openingPrice),
        high = Money(this.highPrice),
        low = Money(this.lowPrice),
        trade = Money(this.tradePrice),
        current = this.timestamp.toDate(),
        accPrice = Money(this.candleAccTradePrice),
        accVolume = this.candleAccTradeVolume
    )
}

internal fun MinuteResponse.toDomain() : MinuteCandle {
    return MinuteCandle(
        market = Market.fromCode(this.market) ?: Market.KRW_BTC,
        time = this.candleDateTimeUtc.toDate(),
        open = Money(this.openingPrice),
        high = Money(this.highPrice),
        low = Money(this.lowPrice),
        trade = Money(this.tradePrice),
        current = this.timestamp.toDate(),
        accPrice = Money(this.candleAccTradePrice),
        accVolume = this.candleAccTradeVolume,
        unit = this.unit
    )
}