package com.example.hnmobiletest.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hnmobiletest.data.News
import com.example.hnmobiletest.models.NewsResponse
import com.example.hnmobiletest.models.SessionData
import com.example.hnmobiletest.utils.retrofit.APIClient
import com.example.hnmobiletest.utils.retrofit.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.Comparator


object NewsMobileRepository {

    var apiInterface: APIInterface? = null
    private val newsResponse = MutableLiveData<NewsResponse>()

    fun getRandomUsers(): MutableLiveData<NewsResponse> {

        apiInterface = APIClient.getClient()?.create(APIInterface::class.java)
        val call = apiInterface!!.getLatestNews()
        call.enqueue(object : Callback<NewsResponse> {
            @Override
            override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>,
            ) {
                val usersResponse = response.body()

                usersResponse?.let {
                    newsResponse.value = it
                }
                if (SessionData.news.size == 0) {
                    newsResponse.value!!.hits.sortWith(Comparator { o1, o2 -> o1.created_at!!.compareTo(o2.created_at!!) })
                    for (i in newsResponse.value!!.hits) {
                        val news = News(id = 0,
                                created_at = i.created_at,
                                url = i.url,
                                story_url = i.story_url,
                                title = i.title,
                                author = i.author,
                                objectID = i.objectID,
                                story_title = i.story_title)
                        SessionData.news.add(news)

                    }
                }

            }

            @Override
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR", t.toString())
                SessionData.noInternet = true
                newsResponse.value = null
            }
        })

        return newsResponse
    }
}