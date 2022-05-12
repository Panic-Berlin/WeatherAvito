package com.example.weatheravito.features.temperature.domain

import com.example.weatheravito.features.temperature.data.TemperatureRepo
import com.example.weatheravito.utils.safeRequest
import javax.inject.Inject
/**
 * Make in october 2021 by Magomedov Arslan
 */
class TemperatureInteractor @Inject constructor(
    private val temperatureRepo: TemperatureRepo
) {
    suspend fun getDaily(key: String) = safeRequest {
        temperatureRepo.getDaily(key)
    }

    suspend fun getFiveDay(key: String) = safeRequest {
        temperatureRepo.getFiveDay(key)

    }
}
