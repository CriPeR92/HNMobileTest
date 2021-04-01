package com.example.hnmobiletest.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NewsRoomViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<News>>
    private val repository: NewsRepository

    init {
        val newsDao = NewsDatabase.getDatabase(application).newsDao()
        repository = NewsRepository(newsDao)
        readAllData = repository.readAllData
    }

    fun addNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNews(news)
        }
    }

    fun updateNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNews(news)
        }
    }

    fun deleteNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNews(news)
        }
    }
}