package com.example.weatheravito.features.cities.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortCity(
    val key: String,
    val localizedName: String,
    val country: Country,
    val administrativeArea: AdministrativeArea,
): Parcelable

@Parcelize
class Country(
    val localizedName: String
): Parcelable
@Parcelize
class AdministrativeArea(
    val localizedName: String
):Parcelable