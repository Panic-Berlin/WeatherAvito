package com.example.weatheravito.features.cities.data.api

import com.example.weatheravito.features.cities.data.model.CityRes
import com.example.weatheravito.features.temperature.data.api.API_KEY
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CityApi {
    @GET("locations/v1/cities/autocomplete?language=ru-ru")
    suspend fun getSearchList(
        @Query("apikey") key: String = API_KEY,
        @Query("q") q: String
    ): List<CityRes>

    @GET("locations/v1/topcities/{top}?language=ru-ru")
    suspend fun getTopCities(
        @Path("top") top: String = "50",
        @Query("apikey") key: String = API_KEY
    ): List<CityRes>


}