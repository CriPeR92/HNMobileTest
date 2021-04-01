package com.example.hnmobiletest.utils.retrofit


import com.example.hnmobiletest.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface APIInterface {
    @GET("api/v1/search_by_date?query=mobile")
    fun getLatestNews(): Call<NewsResponse>
}