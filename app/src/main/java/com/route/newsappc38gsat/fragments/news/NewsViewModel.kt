package com.route.newsappc38gsat.fragments.news

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.SourcesItemDTO
import com.example.domain.repos.news.NewsRepository
import com.example.domain.repos.sources.SourcesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

// Lifecycle Aware
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val sourcesRepository: SourcesRepository,
) : ViewModel() {
    // MVI (Add-On UI Architecture Pattern)
    // Model-View-Intent()


    // State
    val states = mutableStateOf<NewsViewStates>(value = NewsViewStates.Idle)
    val sourcesState = mutableStateOf<List<SourcesItemDTO>>(listOf())
    val eventsChannel = Channel<NewsViewEvents>(Channel.UNLIMITED)
    val selectedIndex = mutableIntStateOf(0)

    init {
        processNewsEventsClicks()
    }

    /***
     *  *  Activity :-
     *       1 - Send Events To View Model
     *       2- Render States
     *  ViewModel :-
     *      1- Process Events
     *      2- Reduce States
     */
    fun processNewsEventsClicks() {
        Log.e("TAG", "Hello world")
        viewModelScope.launch {
            eventsChannel.consumeAsFlow().collect {
                when (it) {
                    is NewsViewEvents.Idle -> {
                    }

                    is NewsViewEvents.ClickedOnNewsItem -> {
                        // navigate to news Details Screen
                    }

                    is NewsViewEvents.SelectTabEvent -> {
                        // getNewsBySource ->
                        Log.e("TAG", "YES I CALLED BLOCK")
                        getNewsBySource(it.sourceId)

                    }

                    is NewsViewEvents.EnteredNewsScreen -> {
                        getNewsTabsFromAPI(categoryId = it.category) { sourceId ->
                            getNewsBySource(sourceId = sourceId)
                        }

                    }
                }

            }

        }

    }
    // Reduce States
    // High level Modules :-
//        - View Model
    // News Repository :-
//            Offline Data Source
//            Online Data Source
//            Network Handler
    // Dependency Injection :- is Application of Dependency inversion Principle
    // SOLI'D' :-  Dependency inversion
    // Dagger (Hilt)
    // Dependency Injection Library

    private fun getNewsBySource(sourceId: String) {
        /**
         *
         * Parallelism
         *         // new Thread
         */
        // Concurrency

        viewModelScope.launch(Dispatchers.IO) {
            try {
                states.value = NewsViewStates.Loading
                val result = newsRepository.getNewsData(sourceId)
                states.value =
                    NewsViewStates.LoadedSources(
                        newsList = result,
                        sources = sourcesState.value
                    )
                withContext(Dispatchers.Main) {
//                    newsList.value = result ?: listOf()
                }
            } catch (ex: Exception) {
                Log.e("Tag", "${ex.message}")
                states.value = NewsViewStates.Error(ex.message ?: "")
            }
        }
    }

    private fun getNewsTabsFromAPI(categoryId: String?, onResponse: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response =
                    sourcesRepository.getSourcesData(categoryId ?: "")
                if (response.isNotEmpty())
                    onResponse(response.get(0).id)
                sourcesState.value = response
                states.value =
                    NewsViewStates.LoadedSources(sources = response, listOf())
            } catch (ex: Exception) {
                Log.e("Tag", "${ex.message}")
                states.value = NewsViewStates.Error(ex.message ?: "")
            }
        }
    }
}
