package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.api.model.SourcesItem

@Dao
interface SourcesDao {
    @Insert(SourcesItem::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSources(newsList: List<SourcesItem>)

    @Query("SELECT * from SourcesItem")
    suspend fun getSources(): List<SourcesItem>
}