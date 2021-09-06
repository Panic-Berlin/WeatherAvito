package com.example.weatheravito.features.temperature.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime

class LocalDateTimeAdapter {
    @FromJson
    fun fromJson(string: String) = DateTime(string)

    @ToJson
    fun toJson(value: DateTime) = value.toString()
}