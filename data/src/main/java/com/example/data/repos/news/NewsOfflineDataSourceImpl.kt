package com.example.data.repos.news

import com.example.data.api.model.ArticlesItem
import com.example.data.api.model.convertTo
import com.example.data.database.NewsLocalDatabase
import com.example.domain.entity.ArticlesItemDTO
import com.example.domain.repos.news.NewsOfflineDataSource

class NewsOfflineDataSourceImpl(val newsDataBase: NewsLocalDatabase) : NewsOfflineDataSource {
    override suspend fun getNewsFromLocalDB(): List<ArticlesItemDTO> {
        try {
            val news = newsDataBase.newsDao().getNews()
            return news.map { it.convertTo(ArticlesItemDTO::class.java) }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun saveNewsIntoLocalDB(newsList: List<ArticlesItemDTO>) {
        try {
            newsDataBase.newsDao()
                .insertNews(newsList.map { it.convertTo(ArticlesItem::class.java) })
        } catch (ex: Exception) {
            throw ex
        }
    }
}