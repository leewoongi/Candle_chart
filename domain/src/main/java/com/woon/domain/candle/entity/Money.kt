package com.woon.domain.candle.entity

import java.text.DecimalFormat

data class Money(
    val value: Double
){
    constructor(value: String) : this(value.toDouble())
    constructor(value: Long) : this(value.toDouble())
    constructor(value: Int) : this(value.toDouble())

    // 연산자 오버로딩
    operator fun plus(other: Money): Money = Money(value + other.value)
    operator fun minus(other: Money): Money = Money(value - other.value)
    operator fun times(multiplier: Double): Money = Money(value * multiplier)
    operator fun div(divisor: Double): Money = Money(value / divisor)

    // 비교 연산자
    operator fun compareTo(other: Money): Int = value.compareTo(other.value)

    // 유틸리티 함수들
    fun abs(): Money = Money(kotlin.math.abs(value))

    // 포맷팅 (정수로 표시)
    fun toFormattedString(): String {
        return DecimalFormat("#,###").format(value.toLong())
    }

    fun toCurrencyString(symbol: String = "₩"): String {
        return "$symbol${toFormattedString()}"
    }

    // 백분율 계산
    fun changeRate(from: Money): Double {
        if (from.value == 0.0) return 0.0
        return ((value - from.value) / from.value * 100)
    }

    // 변환
    fun toDouble(): Double = value
    fun toLong(): Long = value.toLong()
    fun toInt(): Int = value.toInt()

    // 정수로 표시 (소수점 제거)
    override fun toString(): String = value.toLong().toString()

    companion object {
        val ZERO = Money(0.0)

        fun max(a: Money, b: Money): Money = if (a >= b) a else b
        fun min(a: Money, b: Money): Money = if (a <= b) a else b
    }
}
