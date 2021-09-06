package com.example.weatheravito.features.temperature.domain.model

import org.joda.time.DateTime
import java.util.*

data class ShortTemperature(
    val headline: Headline,
    var dailyForecasts: List<DailyForecasts>
)

class Headline(
    val effectiveDate: DateTime,
    val text: String,
    val category: String
)

class DailyForecasts(
    val temperature: Temperature,
    val day: Day,
    val night: Night,
    val date: DateTime
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
    val iconPhrase: String,
    val precipitationType: String
)

class Night(
    val iconPhrase: String
)