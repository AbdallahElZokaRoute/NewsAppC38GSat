package com.example.data.di

import android.content.Context
import com.example.data.database.NewsLocalDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNewsLocalDatabase(
        @ApplicationContext context: Context): NewsLocalDatabase {
        NewsLocalDatabase.init(context)
        return NewsLocalDatabase.getInstance()
    }

}