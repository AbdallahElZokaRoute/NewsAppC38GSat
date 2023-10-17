package com.example.data.repos.sources

import com.example.domain.entity.SourcesItemDTO
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.sources.SourcesOfflineDataSource
import com.example.domain.repos.sources.SourcesOnlineDataSource
import com.example.domain.repos.sources.SourcesRepository

class SourcesRepositoryImpl(
    val sourcesOnlineDataSource: SourcesOnlineDataSource,
    val sourcesOfflineDataSource: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler,
) : SourcesRepository {
    override suspend fun getSourcesData(categoryID: String): List<SourcesItemDTO> {
        try {
            return if (networkHandler.isOnline()) {
                val list = sourcesOnlineDataSource.getSourcesFromAPI(categoryID)
                sourcesOfflineDataSource.saveSourcesIntoDB(list)
                list

            } else
                sourcesOfflineDataSource.getSourcesFromDB()
        } catch (ex: Exception) {
            throw ex
        }
    }

}
