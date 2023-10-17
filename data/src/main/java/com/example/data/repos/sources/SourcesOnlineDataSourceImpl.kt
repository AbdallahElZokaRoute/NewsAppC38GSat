package com.example.data.repos.sources

import com.example.data.Constants
import com.example.data.api.NewsServices
import com.example.data.api.model.convertTo
import com.example.domain.entity.SourcesItemDTO
import com.example.domain.entity.SourcesResponseDTO
import com.example.domain.repos.sources.SourcesOnlineDataSource

class SourcesOnlineDataSourceImpl(val newsServices: NewsServices) : SourcesOnlineDataSource {
    override suspend fun getSourcesFromAPI(categoryID: String): List<SourcesItemDTO> {
        try {
            return newsServices.getNewsSources(Constants.API_KEY, categoryID)
                .convertTo(SourcesResponseDTO::class.java).sources ?: listOf()
        } catch (ex: Exception) {
            throw ex
        }
    }

}