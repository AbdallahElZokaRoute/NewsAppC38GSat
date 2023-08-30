package com.route.newsappc38gsat.apis

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines/sources")
    fun getNewsSources(@Query("apiKey") apiKey: String): Call<SourcesResponse>

}