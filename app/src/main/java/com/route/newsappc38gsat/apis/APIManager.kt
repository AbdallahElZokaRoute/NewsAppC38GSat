package com.route.newsappc38gsat.apis

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManager private constructor() {

    companion object {
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit!!
        }

        fun getNewsServices(): NewsServices {
            return getInstance().create(NewsServices::class.java)

        }

    }

}