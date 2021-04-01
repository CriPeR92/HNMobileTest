package com.example.hnmobiletest.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNews(news: News)

    @Update
    suspend fun updateNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)

    @Query("DELETE FROM news_table")
    suspend fun deleteAllNewss()

    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<News>>
}
