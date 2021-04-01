package com.example.hnmobiletest.models

import com.example.hnmobiletest.data.News

class SessionData {

    companion object {
        var news : MutableList<News> = ArrayList()
        var newsData: News? = null
        var newsToDelete: News? = null
        var noInternet = false
    }
}