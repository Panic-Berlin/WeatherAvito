package com.example.weatheravito.features.temperature.di

import okhttp3.CacheControl

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
/**
 * Make in october 2021 by Magomedov Arslan
 */
class CacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(CACHE_MAX_LIVE, TimeUnit.HOURS) // 15 minutes cache
            .build()
        return response.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}

private const val CACHE_MAX_LIVE = 3
