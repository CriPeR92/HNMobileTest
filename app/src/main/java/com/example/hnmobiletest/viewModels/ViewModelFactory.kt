package com.example.hnmobiletest.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hnmobiletest.models.Hits

class ViewModelFactory(private val news: Hits?) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WebViewViewModel(news) as T
    }

}