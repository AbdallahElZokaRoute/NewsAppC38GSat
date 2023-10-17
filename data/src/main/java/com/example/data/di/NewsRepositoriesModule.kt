package com.example.data.di

import com.example.data.api.NewsServices
import com.example.data.database.NewsLocalDatabase
import com.example.data.repos.news.NewsOfflineDataSourceImpl
import com.example.data.repos.news.NewsOnlineDataSourceImpl
import com.example.data.repos.news.NewsRepositoryImpl
import com.example.domain.repos.NetworkHandler
import com.example.domain.repos.news.NewsOfflineDataSource
import com.example.domain.repos.news.NewsOnlineDataSource
import com.example.domain.repos.news.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsRepositoriesModule {
    @Provides
    @Singleton
    fun provideNewsRepository(
        onlineDataSource: NewsOnlineDataSource,
        offlineDataSource: NewsOfflineDataSource,
        networkHandler: NetworkHandler
    ): NewsRepository {
        return NewsRepositoryImpl(onlineDataSource, offlineDataSource, networkHandler)
    }

    @Provides
    @Singleton
    fun provideNewsOnlineDataSource(webServices: NewsServices): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webServices)
    }

    @Provides
    @Singleton
    fun provideNewsOfflineDataSource(newsDB: NewsLocalDatabase): NewsOfflineDataSource {
        return NewsOfflineDataSourceImpl(newsDB)
    }

    @Provides
    @Singleton
    fun provideNetworkHandler(): NetworkHandler {
        return object : NetworkHandler {
            override fun isOnline(): Boolean {
                return true
            }

        }
    }
}