package com.example.hnmobiletest.viewModels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hnmobiletest.data.News
import com.example.hnmobiletest.models.NewsResponse
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.repository.NewsMobileRepository

class NewsViewModel : ViewModel() {

    val isLoading = ObservableBoolean()
    var newsResponse = MutableLiveData<NewsResponse>()
    val uiEventValue = MutableLiveData<Int>()
    private val userRepository: NewsMobileRepository by lazy {
        NewsMobileRepository
    }
    init {
        newsResponse = userRepository.getRandomUsers()
    }

    fun onClickActionGridAdapter(news: News, type: Int) {
        SessionData.newsData = news
        onActionViewModel(type)
    }

    fun onClickDelete(news: News, type: Int) {
        SessionData.newsToDelete = news
        SessionData.news.remove(news)
        onActionViewModel(type)
    }

    private fun onActionViewModel(type: Int) {
        uiEventValue.value = type
    }

    fun onRefresh() {
        isLoading.set(true)
        newsResponse = userRepository.getRandomUsers()
    }

}