package com.example.data.di

import com.example.data.api.NewsServices
import com.example.data.database.NewsLocalDatabase
import com.example.data.repos.sources.SourcesOfflineDataSourceImpl
import com.example.data.repos.sources.SourcesOnlineDataSourceImpl
import com.example.data.repos.sources.SourcesRepositoryImpl
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.sources.SourcesOfflineDataSource
import com.example.domain.repos.sources.SourcesOnlineDataSource
import com.example.domain.repos.sources.SourcesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Whole Application
object SourcesRepositoriesModule {
    @Provides
    @Singleton
    fun provideSourcesRepository(
        onlineSourcesOnlineDataSource: SourcesOnlineDataSource,
        offlineSourcesDataSource: SourcesOfflineDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(
            onlineSourcesOnlineDataSource,
            offlineSourcesDataSource,
            networkHandler
        )
    }

    @Provides
    @Singleton
    fun provideSourcesOnlineDataSource(
        newsServices: NewsServices
    ): SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(newsServices = newsServices)
    }

    @Provides
    @Singleton
    fun provideSourcesOfflineDataSource(
        newsLocalDatabase: NewsLocalDatabase
    ): SourcesOfflineDataSource {
        return SourcesOfflineDataSourceImpl(newsLocalDatabase)
    }
}