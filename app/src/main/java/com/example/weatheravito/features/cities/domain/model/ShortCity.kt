package com.example.weatheravito.features.cities.domain.model

import java.io.Serializable

data class ShortCity(
    val key: String,
    val localizedName: String,
    val country: Country,
    val administrativeArea: AdministrativeArea,
): Serializable

class Country(
    val localizedName: String
)

class AdministrativeArea(
    val localizedName: String
)