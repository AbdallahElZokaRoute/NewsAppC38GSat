package com.example.data.repos.news


import com.example.domain.entity.ArticlesItemDTO
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.news.NewsOfflineDataSource
import com.example.domain.repos.news.NewsOnlineDataSource
import com.example.domain.repos.news.NewsRepository
import java.lang.Exception

class NewsRepositoryImpl(
    val onlineDataSource: NewsOnlineDataSource,
    val offlineDataSource: NewsOfflineDataSource,
    val networkHandler: NetworkHandler
) : NewsRepository {
    override suspend fun getNewsData(sourceId: String): List<ArticlesItemDTO> {
        try {
            return if (networkHandler.isOnline()) {
                val articles = onlineDataSource.getNewsFromAPI(sourceId)
                offlineDataSource.saveNewsIntoLocalDB(articles)
                articles
            } else
                offlineDataSource.getNewsFromLocalDB()

        } catch (ex: Exception) {
            throw ex
        }
    }


}
