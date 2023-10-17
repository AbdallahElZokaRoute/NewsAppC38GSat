package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.api.model.ArticlesItem


@Dao
interface NewsDao {
    @Insert(ArticlesItem::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(newsList: List<ArticlesItem>)

    @Query("SELECT * from ArticlesItem")
    suspend fun getNews(): List<ArticlesItem>
}
