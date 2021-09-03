package com.example.weatheravito.features.temperature.domain

import com.example.weatheravito.features.temperature.data.TemperatureRepo
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import javax.inject.Inject

class TemperatureInteractor @Inject constructor(
    private val temperatureRepo: TemperatureRepo
) {
    suspend fun getDaily(key: String): ShortTemperature {
        return temperatureRepo.getDaily(key)
    }

    suspend fun getFiveDay(key: String): ShortTemperature {
        return temperatureRepo.getFiveDay(key)
    }
}