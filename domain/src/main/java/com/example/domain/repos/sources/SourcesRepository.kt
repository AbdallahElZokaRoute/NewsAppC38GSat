package com.example.domain.repos.sources

import com.example.domain.entity.SourcesItemDTO


interface SourcesRepository {
    suspend fun getSourcesData(categoryID: String): List<SourcesItemDTO>
}

interface SourcesOnlineDataSource {
    suspend fun getSourcesFromAPI(categoryID: String): List<SourcesItemDTO>
}

interface SourcesOfflineDataSource {
    suspend fun getSourcesFromDB(): List<SourcesItemDTO>
    suspend fun saveSourcesIntoDB(sourcesList: List<SourcesItemDTO>)
}