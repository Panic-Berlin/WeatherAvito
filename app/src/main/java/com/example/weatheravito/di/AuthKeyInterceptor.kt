package com.example.weatheravito.di

import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "w5vIvuGCGaORlx5myVMEkcJxkBAGBdjy"

class AuthKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val updatedUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("apikey", API_KEY)
            .build()
        return chain.proceed(chain.request().newBuilder().url(updatedUrl).build())
    }
}