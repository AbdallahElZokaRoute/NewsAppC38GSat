package com.example.data.repos.sources

import com.example.data.api.model.SourcesItem
import com.example.data.api.model.convertTo
import com.example.data.database.NewsLocalDatabase
import com.example.domain.entity.SourcesItemDTO
import com.example.domain.repos.sources.SourcesOfflineDataSource

class SourcesOfflineDataSourceImpl(val newsLocalDatabase: NewsLocalDatabase) :
    SourcesOfflineDataSource {
    override suspend fun getSourcesFromDB(): List<SourcesItemDTO> {
        try {
            return newsLocalDatabase.sourcesDao().getSources()
                .map { it.convertTo(SourcesItemDTO::class.java) }
        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun saveSourcesIntoDB(sourcesList: List<SourcesItemDTO>) {
        try {
            return newsLocalDatabase.sourcesDao()
                .insertSources(sourcesList.map { it.convertTo(SourcesItem::class.java) })
        } catch (ex: Exception) {
            throw ex
        }
    }

}