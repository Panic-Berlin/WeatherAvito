package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.NightRes
import com.example.weatheravito.features.temperature.domain.model.Night
import javax.inject.Inject

class NightMapper @Inject constructor() {
    fun map(nightRes: NightRes) = Night(
        iconPhrase = nightRes.iconPhrase
    )
}