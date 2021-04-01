package com.example.hnmobiletest.viewModels

import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hnmobiletest.data.News


class WebViewViewModel(news: News?): ViewModel() {

    val uiEventValue = MutableLiveData<Int>()

    private val _url = MutableLiveData(if (news?.url != null) {
        news.url
    }else {
        news?.story_url
    })
    private val _hide = MutableLiveData(1)

    val url: MutableLiveData<String?> = _url
    val hide: MutableLiveData<Int> = _hide

    fun getWebViewClient() : WebViewClient {
        return Client(hide)
    }

    fun goBack() {
        uiEventValue.value = 1
    }

    class Client(hide: MutableLiveData<Int>) : WebViewClient() {
        private val hideEvent = hide
        override fun onReceivedError(view: WebView, request: WebResourceRequest,
                                     error: WebResourceError) {
            super.onReceivedError(view, request, error)

        }

        override fun onPageFinished(view: WebView, url: String) {
            hideEvent.value = 0
            super.onPageFinished(view, url)
        }


    }

}