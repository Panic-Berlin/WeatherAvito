package com.example.weatheravito.features.temperature.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ShortTemperature(
    val headline: Headline,
    var dailyForecasts: List<DailyForecasts>
)

class Headline(
    val text: String
)

class DailyForecasts(
    val temperature: Temperature,
    val day: Day,
    val night: Night
)

class Temperature(
    val minimum: Minimum,
    val maximum: Maximum
)

class Minimum(
    val value: Int,
    val unit: String
) {
    val valueInC: Int
        get() = (value - 32) * 5 / 9
}

class Maximum(
    val value: Int,
    val unit: String
) {
    val valueInC: Int
        get() = (value - 32) * 5 / 9
}

class Day(
    val iconPhrase: String
)

class Night(
    val iconPhrase: String
)