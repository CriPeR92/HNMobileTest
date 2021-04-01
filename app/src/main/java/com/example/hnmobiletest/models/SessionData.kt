package com.example.hnmobiletest.models

class SessionData {

    companion object {

        val news : MutableList<Hits> = ArrayList()
        var isLoading = false

        var newsData: Hits? = null
    }
}