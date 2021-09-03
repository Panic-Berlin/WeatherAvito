package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.MaximumRes
import com.example.weatheravito.features.temperature.domain.model.Maximum
import javax.inject.Inject

class MaximumMapper @Inject constructor() {
    fun map(maximumRes: MaximumRes) = Maximum(
        value = maximumRes.value,
        unit = maximumRes.unit
    )
}