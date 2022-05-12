package com.example.weatheravito.features.temperature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.joda.time.DateTime
import org.joda.time.LocalDateTime
/**
 * Make in october 2021 by Magomedov Arslan
 */
@JsonClass(generateAdapter = true)
data class TemperatureResponse(
    @Json(name = "Headline")
    val headline: HeadlineRes,
    @Json(name = "DailyForecasts")
    var dailyForecasts: List<DailyForecastsRes>
)

@JsonClass(generateAdapter = true)
class HeadlineRes(
    @Json(name = "EffectiveDate")
    val effectiveDate: DateTime,
    @Json(name = "Text")
    val text: String,
    @Json(name = "Category")
    val category: String?
)

@JsonClass(generateAdapter = true)
class DailyForecastsRes(
    @Json(name = "Temperature")
    val temperature: TemperatureRes,
    @Json(name = "Day")
    val day: DayRes,
    @Json(name = "Night")
    val night: NightRes,
    @Json(name = "Date")
    val date: DateTime
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
    val iconPhrase: String,
    @Json(name = "PrecipitationType")
    val precipitationType: String?
)

@JsonClass(generateAdapter = true)
class NightRes(
    @Json(name = "IconPhrase")
    val iconPhrase: String
)

