package com.example.weatheravito.features.temperature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TemperatureResponse(
    @Json(name = "Headline")
    val headline: HeadlineRes,
    @Json(name = "DailyForecasts")
    var dailyForecasts: List<DailyForecastsRes>
)

@JsonClass(generateAdapter = true)
class HeadlineRes(
    @Json(name = "Text")
    val text: String
)

@JsonClass(generateAdapter = true)
class DailyForecastsRes(
    @Json(name = "Temperature")
    val temperature: TemperatureRes,
    @Json(name = "Day")
    val day: DayRes,
    @Json(name = "Night")
    val night: NightRes
)

@JsonClass(generateAdapter = true)
class TemperatureRes(
    @Json(name = "Minimum")
    val minimum: MinimumRes,
    @Json(name = "Maximum")
    val maximum: MaximumRes
)

@JsonClass(generateAdapter = true)
class MinimumRes(
    @Json(name = "Value")
    val value: Int,
    @Json(name = "Unit")
    val unit: String
)

@JsonClass(generateAdapter = true)
class MaximumRes(
    @Json(name = "Value")
    val value: Int,
    @Json(name = "Unit")
    val unit: String
)

@JsonClass(generateAdapter = true)
class DayRes(
    @Json(name = "IconPhrase")
    val iconPhrase: String
)

@JsonClass(generateAdapter = true)
class NightRes(
    @Json(name = "IconPhrase")
    val iconPhrase: String
)

