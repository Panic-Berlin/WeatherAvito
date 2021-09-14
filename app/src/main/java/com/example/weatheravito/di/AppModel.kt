package com.example.weatheravito.di

import android.content.Context
import com.example.weatheravito.features.temperature.data.adapter.LocalDateTimeAdapter
import com.example.weatheravito.features.temperature.di.CacheInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModel {

    @Singleton
    @Provides
    fun provideOkHttp(@ApplicationContext applicationContext: Context): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            .addInterceptor(AuthKeyInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .cache(applicationContext.let {
                val httpCacheDirectory = File(it.cacheDir, "http-cache")
                val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
                Cache(httpCacheDirectory, cacheSize)
            })
            .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(LocalDateTimeAdapter())
        .build()

    @Singleton
    @Provides
    fun provideWeatherApiRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(WEATHER_BASE_URL)
        .client(okHttpClient)
        .build()
}

private const val WEATHER_BASE_URL = "https://dataservice.accuweather.com/"