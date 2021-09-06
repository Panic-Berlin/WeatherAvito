package com.example.weatheravito.features.temperature.data.model.mapper

import com.example.weatheravito.features.temperature.data.model.HeadlineRes
import com.example.weatheravito.features.temperature.domain.model.Headline
import javax.inject.Inject

class HeadlineMapper @Inject constructor() {
    fun map(headlineRes: HeadlineRes) = Headline(
        text = headlineRes.text,
        effectiveDate = headlineRes.effectiveDate,
        category = headlineRes.category.orEmpty()
    )
}