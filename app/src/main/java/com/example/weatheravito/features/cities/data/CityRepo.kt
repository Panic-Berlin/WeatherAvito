package com.example.weatheravito.features.cities.data

import com.example.weatheravito.features.cities.data.api.CityApi
import com.example.weatheravito.features.cities.data.model.mapper.CityMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CityRepo @Inject constructor(
    private val cityApi: CityApi,
    private val cityMapper: CityMapper
) {
    suspend fun getTop50Cities() = withContext(Dispatchers.IO) {
        cityApi.getTopCities().let { res -> res.map { cityMapper.map(it) } }
    }

    suspend fun getSearchList(q: String) = withContext(Dispatchers.IO) {
        cityApi.getSearchList(q = q).let { res -> res.map { cityMapper.map(it) } }
    }
}