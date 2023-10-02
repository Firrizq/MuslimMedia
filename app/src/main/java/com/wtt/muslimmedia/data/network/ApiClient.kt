package com.wtt.muslimmedia.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    val client = OkHttpClient.Builder()
        .addInterceptor{
            val request = it.request()
                .newBuilder()
                .addHeader("X-Api-Key", "9605307d023f4ad392e348ad910132f1")
                .build()
            it.proceed(request)
        }
        .readTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    fun getApiService(): ApiService{
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}