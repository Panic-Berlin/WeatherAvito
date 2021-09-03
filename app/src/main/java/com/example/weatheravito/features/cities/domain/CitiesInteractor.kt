package com.example.weatheravito.features.cities.domain

import com.example.weatheravito.features.cities.data.CityRepo
import com.example.weatheravito.features.cities.domain.model.ShortCity
import javax.inject.Inject

class CitiesInteractor @Inject constructor(
    private val cityRepo: CityRepo
) {
    suspend fun getCity(): List<ShortCity> {
        return cityRepo.getTop50Cities()
    }

    suspend fun getSearchList(query: String): List<ShortCity> {
        return cityRepo.getSearchList(query)
    }
}