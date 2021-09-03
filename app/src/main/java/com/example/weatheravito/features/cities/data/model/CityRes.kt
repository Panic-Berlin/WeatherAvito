package com.example.weatheravito.features.cities.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class CityRes(
    @Json(name = "Key")
    val key: String,
    @Json(name = "LocalizedName")
    val localizedName: String,
    @Json(name = "Country")
    val county: CountryRes,
    @Json(name = "AdministrativeArea")
    val administrativeArea: AdministrativeAreaRes
)

@JsonClass(generateAdapter = true)
class CountryRes(
    @Json(name = "LocalizedName")
    val localizedName: String
)

@JsonClass(generateAdapter = true)
class AdministrativeAreaRes(
    @Json(name = "LocalizedName")
    val localizedName: String
)