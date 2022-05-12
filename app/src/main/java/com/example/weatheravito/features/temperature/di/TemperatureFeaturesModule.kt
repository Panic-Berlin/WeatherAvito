package com.example.weatheravito.features.temperature.di

import com.example.weatheravito.features.temperature.data.api.TemperatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
/**
 * Make in october 2021 by Magomedov Arslan
 */
@InstallIn(SingletonComponent::class)
@Module
class TemperatureFeaturesModule {
    @Provides
    fun provideTemperatureApi(retrofit: Retrofit) = retrofit.create(
        TemperatureApi::class.java
    )
}
