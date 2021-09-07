package com.example.weatheravito.features.temperature.domain

import com.example.weatheravito.features.temperature.data.TemperatureRepo
import com.example.weatheravito.features.temperature.domain.model.ShortTemperature
import retrofit2.HttpException
import javax.inject.Inject

class TemperatureInteractor @Inject constructor(
    private val temperatureRepo: TemperatureRepo
) {
    suspend fun getDaily(key: String): ShortTemperature? {
        return try{
            temperatureRepo.getDaily(key)
        }catch (exception: HttpException){
            exception.code()
            null
        }
    }

    suspend fun getFiveDay(key: String): ShortTemperature? {
        return try {
            temperatureRepo.getFiveDay(key)
        }catch (exception: HttpException){
            exception.code()
            null
        }
    }
}