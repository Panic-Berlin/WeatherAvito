package com.example.weatheravito.features.cities.domain

import com.example.weatheravito.features.cities.data.CityRepo
import com.example.weatheravito.features.cities.domain.model.ShortCity
import com.example.weatheravito.utils.safeRequest
import javax.inject.Inject

class CitiesInteractor @Inject constructor(
    private val cityRepo: CityRepo
) {
    suspend fun getCity() = safeRequest {
        cityRepo.getTop50Cities()
    }

    suspend fun getSearchList(query: String) = safeRequest {
        cityRepo.getSearchList(query)
    }
}