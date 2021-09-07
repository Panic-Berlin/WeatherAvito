package com.example.weatheravito.features.temperature.domain

import android.util.Log
import com.example.weatheravito.features.temperature.data.TemperatureRepo
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import com.example.weatheravito.utils.safeRequest
import retrofit2.HttpException
import javax.inject.Inject

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