package com.example.data.api

import com.example.data.api.model.NewsResponse
import com.example.data.api.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") categoryId: String?,
    ): SourcesResponse

    @GET("everything")
    suspend fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String
    ): NewsResponse

}