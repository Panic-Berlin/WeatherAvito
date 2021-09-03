package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.MinimumRes
import com.example.weatheravito.features.temperature.domain.model.Minimum
import javax.inject.Inject

class MinimumMapper @Inject constructor() {
    fun map(minimumRes: MinimumRes) = Minimum(
        value = minimumRes.value,
        unit = minimumRes.unit
    )
}