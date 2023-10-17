package com.route.newsappc38gsat.fragments.news

import com.example.domain.entity.ArticlesItemDTO
import com.example.domain.entity.SourcesItemDTO

sealed class NewsViewStates {
    object Idle : NewsViewStates()
    object Loading : NewsViewStates()
    class LoadedSources(
        val sources: List<SourcesItemDTO>,
        val newsList: List<ArticlesItemDTO>?
    ) : NewsViewStates()

    class Error(val errorMessage: String) : NewsViewStates()

}
