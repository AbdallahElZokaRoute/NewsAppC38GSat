package com.example.domain.repos.news

import com.example.domain.entity.ArticlesItemDTO


interface NewsRepository {
    suspend fun getNewsData(sourceId: String): List<ArticlesItemDTO>
}

interface NewsOnlineDataSource {
    suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItemDTO>
}

interface NewsOfflineDataSource {
    suspend fun getNewsFromLocalDB(): List<ArticlesItemDTO>
    suspend fun saveNewsIntoLocalDB(newsList: List<ArticlesItemDTO>)
}