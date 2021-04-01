package com.example.hnmobiletest.viewModels

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hnmobiletest.models.Hits
import com.example.hnmobiletest.models.NewsResponse
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.repository.NewsMobileRepository

class NewsViewModel : ViewModel() {

    val isLoading = ObservableBoolean()
    var randomUserResponse = MutableLiveData<NewsResponse>()
    val uiEventValue = MutableLiveData<Int>()
    private val userRepository: NewsMobileRepository by lazy {
        NewsMobileRepository
    }
    init {

        randomUserResponse = userRepository.getRandomUsers()
    }

    fun onClickActionGridAdapter(news: Hits, type: Int) {
        SessionData.newsData = news
        onActionViewModel(type)
    }

    fun onClickDelete(news: Hits, type: Int) {
        SessionData.news.remove(news)
        onActionViewModel(type)
    }

//    fun onClickActionSavedUserAdapter(user: UserData) {
//        SessionData.userFragmentSaved = user
//        onActionViewModel(2)
//    }

    private fun onActionViewModel(type: Int) {
        uiEventValue.value = type
    }

    fun onRefresh() {
        isLoading.set(true)
        randomUserResponse = userRepository.getRandomUsers()
    }

}