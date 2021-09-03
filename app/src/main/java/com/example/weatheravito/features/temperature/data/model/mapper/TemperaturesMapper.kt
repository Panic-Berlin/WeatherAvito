package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.TemperatureResponse
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import javax.inject.Inject

class TemperaturesMapper @Inject constructor(
    private val headlineMapper: HeadlineMapper,
    private val dailyForecastsMapper: DailyForecastsMapper
) {
    fun map(temperatureResponse: TemperatureResponse) = ShortTemperature(
        headline = temperatureResponse.headline.let { headlineMapper.map(it) },
        dailyForecasts = temperatureResponse.dailyForecasts.map { dailyForecastsMapper.map(it) }
    )
}