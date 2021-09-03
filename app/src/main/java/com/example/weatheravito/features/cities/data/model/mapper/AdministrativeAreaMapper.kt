package com.example.weatheravito.features.cities.data.model.mapper

import com.example.weatheravito.features.cities.data.model.AdministrativeAreaRes
import com.example.weatheravito.features.cities.domain.model.AdministrativeArea
import javax.inject.Inject

class AdministrativeAreaMapper @Inject constructor() {

    fun map(administrativeAreaRes: AdministrativeAreaRes) = AdministrativeArea(
        localizedName = administrativeAreaRes.localizedName
    )
}