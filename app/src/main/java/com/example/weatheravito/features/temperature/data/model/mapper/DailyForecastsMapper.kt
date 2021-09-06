package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.DailyForecastsRes
import com.example.weatheravito.features.temperature.domain.model.DailyForecasts
import javax.inject.Inject

class DailyForecastsMapper @Inject constructor(
    private val temperatureMapper: TemperatureMapper,
    private val dayMapper: DayMapper,
    private val nightMapper: NightMapper
) {
    fun map(dailyForecastsRes: DailyForecastsRes) = DailyForecasts(
        temperature = dailyForecastsRes.temperature.let { temperatureMapper.map(it) },
        day = dailyForecastsRes.day.let { dayMapper.map(it) },
        night = dailyForecastsRes.night.let { nightMapper.map(it) },
        date = dailyForecastsRes.date
    )
}