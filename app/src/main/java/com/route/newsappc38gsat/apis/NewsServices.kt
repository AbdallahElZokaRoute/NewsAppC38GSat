package com.route.newsappc38gsat.apis

import com.route.newsappc38gsat.apis.model.NewsResponse
import com.route.newsappc38gsat.apis.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") categoryId: String?,
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey: String,
        @Query("sources") sourceId: String
    ): Call<NewsResponse>

}