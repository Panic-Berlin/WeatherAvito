package com.example.weatheravito.features.cities.data.model.mapper

import com.example.weatheravito.features.cities.data.model.CityRes
import com.example.weatheravito.features.cities.domain.model.ShortCity
import javax.inject.Inject

class CityMapper @Inject constructor(
    private val countryMapper: CountryMapper,
    private val administrativeAreaMapper: AdministrativeAreaMapper
) {

    fun map(cityRes: CityRes) = ShortCity(
        key = cityRes.key,
        localizedName = cityRes.localizedName,
        country = cityRes.county.let { countryMapper.map(it) },
        administrativeArea = cityRes.administrativeArea.let { administrativeAreaMapper.map(it) }
    )
}