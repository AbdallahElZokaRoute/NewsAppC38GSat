package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.api.model.ArticlesItem
import com.example.data.api.model.SourcesItem

@Database([ArticlesItem::class, SourcesItem::class], version = 1)
abstract class NewsLocalDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun sourcesDao(): SourcesDao

    companion object {
        var database: NewsLocalDatabase? = null
        fun init(context: Context) {
            if (database == null)
                database = Room.databaseBuilder(
                    context = context.applicationContext,
                    NewsLocalDatabase::class.java,
                    "News Local Database"
                ).fallbackToDestructiveMigration()
                    .build()
        }

        fun getInstance(): NewsLocalDatabase {
            return database!!
        }
    }

}