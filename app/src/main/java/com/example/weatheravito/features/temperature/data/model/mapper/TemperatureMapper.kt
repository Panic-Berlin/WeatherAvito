package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.MaximumRes
import com.example.weatheravito.features.temperature.data.model.MinimumRes
import com.example.weatheravito.features.temperature.data.model.TemperatureRes
import com.example.weatheravito.features.temperature.domain.model.Temperature
import javax.inject.Inject

class TemperatureMapper @Inject constructor(
    private val minimumMapper: MinimumMapper,
    private val maximumMapper: MaximumMapper
) {
    fun map(temperatureRes: TemperatureRes) = Temperature(
        minimum = temperatureRes.minimum.let { minimumMapper.map(it) },
        maximum = temperatureRes.maximum.let { maximumMapper.map(it) }
    )
}