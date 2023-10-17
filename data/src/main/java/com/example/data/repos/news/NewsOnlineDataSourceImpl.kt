package com.example.data.repos.news

import com.example.data.Constants
import com.example.data.api.NewsServices
import com.example.data.api.model.NewsResponse
import com.example.data.api.model.convertTo
import com.example.domain.entity.ArticlesItemDTO
import com.example.domain.entity.NewsResponseDTO
import com.example.domain.repos.news.NewsOnlineDataSource

class NewsOnlineDataSourceImpl(val newsServices: NewsServices) : NewsOnlineDataSource {
    override suspend fun getNewsFromAPI(sourceId: String): List<ArticlesItemDTO> {
        try {
            val response =
                newsServices.getNewsBySource(apiKey = Constants.API_KEY, sourceId = sourceId)
            return response.convertTo(NewsResponseDTO::class.java).articles ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }


}