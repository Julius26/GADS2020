package com.vesencom.gadsphaseii.network.networking

import com.vesencom.gadsphaseii.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private fun clientInstance() =
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
val interceptor = when {
    BuildConfig.DEBUG -> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    else -> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}

private fun retrofitInstance(baseUrl: String): Retrofit =
    Retrofit.Builder()
        .client(clientInstance())
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

private fun formServiceInstance(baseUrl: String) =
    retrofitInstance(baseUrl).create(ApiService::class.java)