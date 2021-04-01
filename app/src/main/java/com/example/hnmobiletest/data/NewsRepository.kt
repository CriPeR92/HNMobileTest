package com.example.hnmobiletest.data

import androidx.lifecycle.LiveData


class NewsRepository(private val newsDao: NewsDao) {

    val readAllData: LiveData<List<News>> = newsDao.readAllData()

    suspend fun addNews(news: News) {
        newsDao.addNews(news)
    }

    suspend fun updateNews(news: News) {
        newsDao.updateNews(news)
    }

    suspend fun deleteNews(news: News) {
        newsDao.deleteNews(news)
    }

}