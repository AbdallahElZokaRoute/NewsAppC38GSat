package com.route.newsappc38gsat.fragments.news

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.route.newsappc38gsat.Constants
import com.route.newsappc38gsat.apis.APIManager
import com.route.newsappc38gsat.apis.model.ArticlesItem
import com.route.newsappc38gsat.apis.model.NewsResponse
import com.route.newsappc38gsat.apis.model.SourcesItem
import com.route.newsappc38gsat.apis.model.SourcesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

// Lifecycle Aware
class NewsViewModel : ViewModel() {
    // State
    val responseState = mutableStateOf(listOf<SourcesItem>())
    val newsList = mutableStateOf(listOf<ArticlesItem>())
    val newsList2 = MutableLiveData<List<ArticlesItem>>()
    val messageState = mutableStateOf("")
    fun getNewsBySource(sourceId: String) {
        /**
         *
         * Parallelism
         *         // new Thread
         */
        // Concurrency
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = APIManager
                    .getNewsServices()
                    .getNewsBySource(Constants.API_KEY, sourceId)
                withContext(Dispatchers.Main) {
                    newsList.value = result.articles ?: listOf()
                }
            } catch (ex: Exception) {
                Log.e("Tag", "${ex.message}")
            }
        }
//            .enqueue(object : Callback<NewsResponse> {
//                override fun onResponse(
//                    call: Call<NewsResponse>,
//                    response: Response<NewsResponse>
//                ) {
//                    val body = response.body()
//                    newsList.value = body?.articles ?: listOf()
//
//                }
//
//                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
//
//
//                }

//    })
        val result = add(30, 45)
    }

    fun add(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    fun getNewsTabsFromAPI(categoryId: String?) {

        viewModelScope.launch {
            try {
                val response =
                    APIManager.getNewsServices().getNewsSources(Constants.API_KEY, categoryId)
                responseState.value = response.sources ?: listOf()
            } catch (ex: Exception) {
                Log.e("Tag", "${ex.message}")
                messageState.value = ex.message ?: "Error Occurred"
            }
        }

        // Runs on background Thread
//            .enqueue(object : Callback<SourcesResponse> {
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    Log.e("TAG", "${response.body()?.status}")
//                    Log.e("TAG", "${response.body()?.sources}")
//                    // Stateful View
//                    val body = response.body()
//                    responseState.value = body?.sources ?: listOf()
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//
//                }
//
//
//            })
    }
}
