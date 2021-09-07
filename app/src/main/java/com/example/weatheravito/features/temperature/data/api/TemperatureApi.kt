package com.example.weatheravito.features.temperature.data.api

import com.example.weatheravito.features.temperature.data.model.TemperatureResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "w5vIvuGCGaORlx5myVMEkcJxkBAGBdjy"

interface TemperatureApi {

    @GET("forecasts/v1/daily/1day/{key}?language=ru-ru")
    suspend fun getDaily(
        @Path("key") cityKey: String,
        @Query("apikey") key: String = API_KEY
    ): TemperatureResponse

    @GET("forecasts/v1/daily/5day/{key}?language=ru-ru")
    suspend fun getFiveDay(
        @Path("key") cityKey: String,
        @Query("apikey") key: String = API_KEY
    ): TemperatureResponse
}