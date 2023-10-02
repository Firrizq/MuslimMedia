package com.wtt.muslimmedia.data.network

import  com.wtt.muslimmedia.data.NewsResponse
import org.intellij.lang.annotations.Language
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    fun getCommonMuslimNews(
        @Query("q") keywords: String = "islam",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "popularity",
        @Query("pageSize") pageSize: Int = 30,
    ) : Call<NewsResponse>

    @GET("everything")
    fun getAboutAlQuranNews(
        @Query("q") keywords: String = "Al-Quran",
        @Query("language") language: String = "en"
    ) : Call<NewsResponse>

    @GET("top-headlines")
    fun getAlJazeeraNews(
        @Query("sources") sources: String = "al-jazeera-english"
    ) : Call<NewsResponse>

    @GET("everything")
    fun getWarningForIslam(
        @Query("q") keywords: String = "anti islam"
    ) : Call<NewsResponse>

    @GET("everything")
    fun searchNews(
        @Query("q") query: String
    ) : Call<NewsResponse>
}