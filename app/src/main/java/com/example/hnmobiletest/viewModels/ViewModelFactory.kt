package com.example.hnmobiletest.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hnmobiletest.data.News

class ViewModelFactory(private val news: News?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WebViewViewModel(news) as T
    }

}