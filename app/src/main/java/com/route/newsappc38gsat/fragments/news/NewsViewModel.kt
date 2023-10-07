package com.route.newsappc38gsat.fragments.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.newsappc38gsat.Constants
import com.route.newsappc38gsat.apis.APIManager
import com.route.newsappc38gsat.apis.model.ArticlesItem
import com.route.newsappc38gsat.apis.model.NewsResponse
import com.route.newsappc38gsat.apis.model.SourcesItem
import com.route.newsappc38gsat.apis.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Lifecycle Aware
class NewsViewModel : ViewModel() {
    // State
    val responseState = mutableStateOf(listOf<SourcesItem>())
    val newsList = mutableStateOf(listOf<ArticlesItem>())
    val newsList2 = MutableLiveData<List<ArticlesItem>>()

    fun getNewsBySource(sourceId: String) {
        APIManager
            .getNewsServices()
            .getNewsBySource(Constants.API_KEY, sourceId)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val body = response.body()
                    newsList.value = body?.articles ?: listOf()

                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {


                }


            })

    }

    fun getNewsTabsFromAPI(categoryId: String?) {
        APIManager.getNewsServices().getNewsSources(Constants.API_KEY, categoryId)
            // Runs on background Thread
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.e("TAG", "${response.body()?.status}")
                    Log.e("TAG", "${response.body()?.sources}")
                    // Stateful View
                    val body = response.body()
                    responseState.value = body?.sources ?: listOf()
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {

                }


            })
    }
}
